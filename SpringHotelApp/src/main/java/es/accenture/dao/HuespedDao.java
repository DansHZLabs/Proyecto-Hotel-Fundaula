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
	public void eliminarHuesped(int idHuesped) {

		/* el id de huesped se obtiene al clicar en la palabra 'eliminar' en la fila
		 correpondiente de la tabla de la vista 'Huespedes.jsp' */
		Huesped huesped = mySessionFactory.getCurrentSession().get(Huesped.class, idHuesped);		

			mySessionFactory.getCurrentSession().delete(huesped);		
			
			//TODO: PONER EXCEPCION NO SE PUEDA BORRAR ADEMAS DE SI ES NULL SI EN LA TABLA DE RESERVAS EL ENUM DEL ESTADO_RESERVA ES IGUAL A 'PENDIENTE' (SI SE PUEDE SE HACE AUTOMATICO POR EL ON DELETE CASCADE QUE PONDRE AL ORM DE HUESPED)
				
		
	}

	//@Transactional  // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)
	//@Override // Se implementa el metodo de la interfaz IHuespedDao
	//public List<Reserva> comprobarReservas(int idHuesped) {
		
		//return  mySessionFactory.getCurrentSession().createQuery("FROM Reserva r WHERE r.idHuesped = :idHuesped", Reserva.class)
			//	.setParameter("idHuesped", idHuesped).getResultList();
		
	//}
	
	
	
	
	
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
		
		List<Huesped> huespedTelefonoCoincidente = mySessionFactory.getCurrentSession()
				.createQuery("FROM Huesped h WHERE h.telefono = :telefono AND h.idHuesped != :id", Huesped.class).
				setParameter("telefono", telefonoFormularioHuesped)
				.setParameter("id", idFormularioHuesped)
				.getResultList();
		
		if (!huespedTelefonoCoincidente.isEmpty()) {
			
			throw new HuespedException(HuespedException.TelefonoDuplicadoException);
		}
		
	}

}
