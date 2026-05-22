package es.accenture.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedException;
import es.accenture.interfaces.IHuespedDao;
import es.accenture.interfaces.IHuespedService;

/**
 * Clase de tipo Servicio que contiene la logica de negocio relativa a la
 * comprobacion de errores antes de la creacion, lectura, modificacion o
 * eliminacion de huespedes por el Dao
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service // Etiqueta Spring que clasifica la clase como Servicio (generando un bean que se pueda utilizar en cualquier momento)
			
public class HuespedService implements IHuespedService {

	private IHuespedDao huespedRepositorio; // Atributo que almacena el DAO de huesped

	/*
	 * Atributo que almacena el objeto de tipo HttpSession (para poder guardar los
	 * datos del usuario Logueado a lo largo de toda su navegacion)
	 */
	private HttpSession session;

	/**
	 * Constructor por parametros en el que se usa la inyeccion de dependencias del
	 * IHuespedDao (su implementacion) y el HttpSession. Se hace aqui y no en el
	 * atributo para que Spring cree al mismo tiempo el objeto de tipo
	 * ServiceUsuario y sus dependencias, evitando un NullPointerException al crear
	 * en primera instancia un InUsuarioDao o un HttpSession null
	 * 
	 * @param repositorioHuesped
	 * @param session
	 */
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.
				
	public HuespedService(IHuespedDao repositorioHuesped, HttpSession session) {

		this.huespedRepositorio = repositorioHuesped;
		this.session = session;

	}

	@Override
	public List<Huesped> listarHuesped() throws Exception {

		List<Huesped> listaHuespedes = huespedRepositorio.listarHuespedes();

		/*
		 * Se comprueba si la lista de huespedes esta vacia para indicarlo en el jsp que
		 * devolvera el controller
		 */
		if (listaHuespedes.isEmpty()) {

			throw new HuespedException(HuespedException.BuscarException);
		}

		return listaHuespedes;
	}

	@Override
	public Huesped buscarHuesped(int idHuesped) {

		return huespedRepositorio.buscarHuesped(idHuesped);

	}

	@Override
	public void actualizarHuesped(Huesped huespedModificado) throws Exception {
		
		/* Comprobamos si estan completos los datos del formulario que no deben
		 * ser null en la BBDD. En caso contrario lanzamos el mensaje 
		 * de error para que el usuario haga los cambios pertinentes. */

		if (huespedModificado.getNombre().isEmpty()
				|| huespedModificado.getApellidos().isEmpty()
				|| huespedModificado.getDireccion().isEmpty()
				|| huespedModificado.getTelefono().isEmpty()) {
			
			throw new HuespedException(HuespedException.ActualizarException);
		}
		
		if (!huespedModificado.getTelefono().isEmpty()) {

			/*
			 * Comprobamos si existe el telefono en la BBDD mediante el getter
			 * de dicho atributo del huesped ORM.
			 * En caso afirmativo mandamos un mensaje de error para que el
			 * usuario haga los cambios pertinentes
			 */
			huespedRepositorio.comprobarDuplicadoTelefonoHuesped(huespedModificado.getTelefono(), huespedModificado.getIdHuesped());
		}

		/*
		 * Si no coinciden los telefonos se salta el condicional y procede a insertar
		 * los datos del huesped (obtenidos del formulario) en la BBDD con el metodo pertinente
		 * del Dao.*/
		huespedRepositorio.actualizarHuesped(huespedModificado);

	}

	
	@Override
	public void eliminarHuesped(int idHuesped) throws Exception {
				
		huespedRepositorio.eliminarHuesped(idHuesped);

	}

	
	@Override
	public void guardarHuesped(Huesped huespedNuevo) throws Exception {
		
		// Misma logica que en actualizar (comentarios detallados en ese metodo), excepto la orden del DAO
		
		if (huespedNuevo.getNombre().isEmpty()
				|| huespedNuevo.getApellidos().isEmpty()
				|| huespedNuevo.getDireccion().isEmpty()
				|| huespedNuevo.getTelefono().isEmpty()) {
			
			throw new HuespedException(HuespedException.ActualizarException);
		}
		
		if (!huespedNuevo.getTelefono().isEmpty()) {

			
			huespedRepositorio.comprobarDuplicadoTelefonoHuesped(huespedNuevo.getTelefono(),0);
		}

		
		huespedRepositorio.guardarHuesped(huespedNuevo);

	}

}
