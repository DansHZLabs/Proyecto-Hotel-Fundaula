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
@Service // Etiqueta Spring que clasifica la clase como Servicio (generando un bean que
			// se pueda utilizar en cualquier momento)
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
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase
				// como 'component' o similar en el paquete seleccionado en la configuracion.
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

		// TODO PREGUNTAR JAVI, este metodo no tiene ninguna excepcion, tal vez poner
		// opcionalmente alguna por si no encuentra un huesped con ese id (esto
		// tecnicamente no deberia pasar pero...)
	}

	@Override
	public void actualizarHuesped(Huesped huespedModificado) throws Exception {

		if (!huespedModificado.getTelefono().isEmpty()) {

			/*
			 * Comprobamos si existe el telefono en la BBDD mediante un metodo que hemos
			 * creado en el DAO En caso afirmativo mandamos un mensaje de error para que el
			 * usuario haga los cambios pertinentes
			 */
			huespedRepositorio.comprobarDuplicadoTelefonoHuesped(huespedModificado.getTelefono());
		}

		/*
		 * Si no coinciden los telefonos se salta el condicional y procede a insertar
		 * los datos del huesped (obtenidos del formulario) en la bbdd con el metodo pertinente
		 * del Dao. En caso de encontrar algun parametro requerido como not null que no se ha rellenado
		 * se lanza una excepcion*/
		huespedRepositorio.actualizarHuesped(huespedModificado);

	}

	@Override
	public void eliminarHuesped(int idHuesped) {
		// TODO PREGUNTAR JAVI poner excepcion si no encuentra ese usuario con ese id

		// TODO poner condicion con reservas de si existe alguna con
		
		huespedRepositorio.eliminarHuesped(idHuesped);

	}

	@Override
	public void guardarHuesped(Huesped huespedNuevo) throws Exception {
		
		if (!huespedNuevo.getTelefono().isEmpty()) {

			/*
			 * Comprobamos si existe el telefono en la BBDD mediante un metodo que hemos
			 * creado en el DAO En caso afirmativo mandamos un mensaje de error para que el
			 * usuario haga los cambios pertinentes
			 */
			huespedRepositorio.comprobarDuplicadoTelefonoHuesped(huespedNuevo.getTelefono());
		}

		/*
		 * Si no coinciden los telefonos se salta el condicional y procede a insertar
		 * los datos del huesped (obtenidos del formulario) en la bbdd con el metodo pertinente
		 * del Dao. En caso de encontrar algun parametro requerido como not null que no se ha rellenado
		 * se lanza una excepcion*/
		huespedRepositorio.guardarHuesped(huespedNuevo);

	}

}
