package es.accenture.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import es.accenture.entity.Reserva;
import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedException;
import es.accenture.interfaces.IHuespedDao;

/**
 * Clase DAO (Data Acces Object) encargada de crear, buscar, modificar o
 * eliminar huespedes en la BBDD 'hoteldb' (los datos identificadores necesarios
 * se envian en la request a partir del formulario del 'Huespedes.jsp').
 * Implementa la interfaz IHuespedDao para generar un mayor desacoplamiento por
 * si en un futuro se quiere utilizar otra clase DAO en vez de esta (y asi tener
 * que cambiar menos cantidad de codigo)*
 * 
 * @author danih y javi
 * @version 1.0
 */
@Component // Anotacion de Spring para mapear la clase como componente (y asi crear un bean que este disponible cuando se necesite)
public class HuespedDao implements IHuespedDao {

	private SessionFactory mySessionFactory; // Atributo donde se almacena la conexion con la BBDD

	/**
	 * Constructor por parametros en el que se usa la inyeccion de dependencias del
	 * SessionFactory. Se hace aqui y no en el atributo para que Spring cree al
	 * mismo tiempo el objeto de tipo HuespedDao y su dependencia SessionFactory,
	 * evitando un NullPointerException (debido a que se crearia un SessionFactory
	 * null)
	 * 
	 * @param sessionFactory
	 */
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.
	public HuespedDao(SessionFactory mySessionFactory) {

		this.mySessionFactory = mySessionFactory;
	}
	

	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public List<Huesped> listarHuespedes() {

		return mySessionFactory.getCurrentSession()
				.createQuery("FROM Huesped h", Huesped.class).getResultList();
	}
	

	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public Huesped buscarHuesped(int idHuesped) {

		/*
		 * el id de huesped se obtiene al clicar en la palabra 'detalle' o 'modificar'
		 * en la fila correpondiente de la tabla de la vista 'Huespedes.jsp'
		 */
		return mySessionFactory.getCurrentSession().get(Huesped.class, idHuesped);
	}
	

	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public void actualizarHuesped(Huesped huespedModificado) throws HuespedException {

		/* el objeto de tipo huesped se envia en la request al darle al boton de guardar
		en el 'FormularioHuesped.jsp'. Viaja desde esa parte pasando por Controller->Service->Dao*/		
		
		mySessionFactory.getCurrentSession().update(huespedModificado);
		
	
	}

	
	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public void eliminarHuesped(int idHuesped) throws HuespedException {

		/* el id de huesped se obtiene al clicar en la palabra 'eliminar' en la fila
		 correpondiente de la tabla de la vista 'Huespedes.jsp' */
		Huesped huesped = mySessionFactory.getCurrentSession().get(Huesped.class, idHuesped);
		
		/* Logica que bloquea el borrado de huespedes si tiene reservas asociadas
		 * sea cual sea el estado (habria que borrar primero manualmente sus reservas
		 * si tuviera alguna). Esto se hace ya que al ser una relacion
		 * 1->N no pueden existir reservas que no tengan asociadas un id de huesped ya que
		 * sino saldria una excepcion que romperia la aplicacion */
		
		List<Reserva> listaReservas = huesped.getReservas();
		
		if(!listaReservas.isEmpty()) {
			
			throw new HuespedException(HuespedException.EliminarException);		
		}

			mySessionFactory.getCurrentSession().delete(huesped);		
		
	}

	
	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public void guardarHuesped(Huesped huespedNuevo) throws HuespedException {

		/* el objeto de tipo huesped se envia en la request al darle al boton de guardar
		en el 'FormularioHuesped.jsp'. Viaja desde esa parte pasando por Controller->Service->Dao*/			
		
		mySessionFactory.getCurrentSession().save(huespedNuevo);

	}
	
	
	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	@Override // Se implementa el metodo de la interfaz IHuespedDao
	public void comprobarDuplicadoTelefonoHuesped (String telefonoFormularioHuesped, int idFormularioHuesped) throws HuespedException {
		
		/* Se crea este metodo para implementar la logica de que
		 * si metemos en el formulario de huespedes un telefono ya existente
		 * en la BBDD nos salte un mensaje de error (ya que en la estructura se ha definido
		 * como unique este campo y sino se romperia la aplicacion con una excepcion) */
		List<Huesped> huespedTelefonoCoincidente = mySessionFactory.getCurrentSession()
				.createQuery("FROM Huesped h WHERE h.telefono = :telefono AND h.idHuesped != :id", Huesped.class). 
				setParameter("telefono", telefonoFormularioHuesped)
				.setParameter("id", idFormularioHuesped)
				.getResultList();
		
		/* los ':' en el query sirven para una preparedStatement, insertando el telefono id con los 
		 * datos que nos llegan del formulario a traves del model
		 */
		
		if (!huespedTelefonoCoincidente.isEmpty()) {
			
			throw new HuespedException(HuespedException.TelefonoDuplicadoException);
		}
		
	}

}
