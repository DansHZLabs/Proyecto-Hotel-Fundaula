package es.accenture.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Usuario;
import es.accenture.exceptions.ExcepcionUsuario;
import es.accenture.interfaces.InServiceUsuario;
import es.accenture.interfaces.InUsuarioDAO;

/**
 * Clase de tipo Servicio que contiene la logica de negocio relativa a la
 * comprobacion de la existencia de las credenciales facilitadas por el usuario
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service // Etiqueta Spring que clasifica la clase como Servicio (generando un bean que se pueda utilizar en cualquier momento)
public class ServiceUsuario implements InServiceUsuario {

	private InUsuarioDAO repositorioUsuario; // Atributo que almacena el DAO de usuario

	/*
	 * Atributo que almacena el objeto de tipo HttpSession (para poder guardar los
	 * datos del usuario Logueado a lo largo de toda su navegacion)
	 */
	private HttpSession session;

	/**
	 * * Constructor por parametros en el que se usa la inyeccion de dependencias
	 * del InUsuarioDAO (su implementacion) y el HttpSession. Se hace aqui y no en
	 * el atributo para que Spring cree al mismo tiempo el objeto de tipo
	 * ServiceUsuario y sus dependencias, evitando un NullPointerException al crear
	 * en primera instancia un InUsuarioDAO o un HttpSessio null
	 * 
	 * @param repositorioUsuario
	 * @param session
	 */
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.	
	public ServiceUsuario(InUsuarioDAO repositorioUsuario, HttpSession session) {

		this.repositorioUsuario = repositorioUsuario;
		this.session = session;
	}

	/*
	 * obtiene los datos de credenciales de usuario (pasados por el controller
	 * apartir del formulario del jsp), los envia al DAO para su comprobacion en la
	 * base de datos y a partir de la logica de negocio establecida, lanza mensajes
	 * de error al Controlador (en caso de que las credenciales esten vacias o sean
	 * incorrectas) o reenvia la informacion verificada de la BBDD para poder
	 * acceder a la siguiente vista (se almacena dicha info en la sesion actual)
	 */
	@Override
	public void loginUsuario(String usuario, String password) throws Exception {

		if (usuario.isEmpty() || password.isEmpty()) {// Estos parametros vienen del formulario 'Login.jsp'

			throw new ExcepcionUsuario(ExcepcionUsuario.CREDENCIALES_VACIAS);

		}

		/*
		 * Se llama al DAO para que haga la consulta en la BBDD y nos devuelva la lista
		 * con coincidencias
		 */
		List<Usuario> listaUsers = repositorioUsuario.obtenerUsuario(usuario);

		if (listaUsers.isEmpty()) {

			throw new ExcepcionUsuario(ExcepcionUsuario.USUARIO_INCORRECTO);
		}

		/*
		 * se obtiene el primer resultado obtenido (se supone que solo puede haber un
		 * usuario con x nombre en la BBDD)
		 */
		Usuario usu = listaUsers.get(0);

		/*
		 * se comprueba que la contrasena introducidad por el usuario coincida con la de
		 * la BBDD
		 */
		if (!usu.getPassword().equals(password)) {

			throw new ExcepcionUsuario(ExcepcionUsuario.PASSWORD_INCORRECTA);
		}

		session.setAttribute("usuarioLogueado", usu); // se guarda la informacion del usuario logueado en la sesion

	}

}
