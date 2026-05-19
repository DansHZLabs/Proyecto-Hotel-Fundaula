package es.accenture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.interfaces.InServiceUsuario;

/**
 * Controlador que devuelve, en funcion de lo que pida la request, la vista
 * inicial de la app o la vista correspondiente al acceso exitoso del login
 * 
 * @author danih y javi
 * @version 1.0
 */
@Controller // Anotacion de Spring para mapear la clase como controlador
public class LoginController {

	/*
	 * Atributo donde se almacena el Servicio de usuario. Se usa la interfaz
	 * y no la clase que la implementa para que exista un mayor
	 * desacoplamiento (por si en un futuro se quiere cambiar la clase que
	 * se encarga de este servicio). Spring detecta automaticamente cual es
	 * la clase que implementa la interfaz (aunque si hay varias hay que
	 * indicar la principal con etiquetas como 'Primary' o 'Qualifier').
	 */
	private InServiceUsuario usuarioService; 

	/**
	 * Constructor por parametros en la que se usa la inyeccion de dependencias del
	 * Servicio de Usuario. Se hace aqui y no en el atributo para que Spring cree al
	 * mismo tiempo el objeto de tipo LoginController y su dependencia
	 * InserviceUsuario, evitando un NullPointerException al crear en primera
	 * instancia un InserviceUsuario null
	 * 
	 * @param usuarioService
	 */
	@Autowired //Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.
	public LoginController(InServiceUsuario usuarioService) {

		this.usuarioService = usuarioService;
	}

	/**
	 * Metodo que recoge todas las request de la URL dirigidas a la raiz del
	 * proyecto, devolviendo la pagina de inicio 'Login' (el viewResolver
	 * configurado en el archivo WebConfig se encarga de añadir las anotaciones de
	 * sub ruta y la extension .jsp correspondientes)
	 * 
	 * @return String con la vista 'Login'
	 */
	@GetMapping("/") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String mostrarLogin() {

		return "Login";
	}

	/**
	 * Metodo que recoge la request de la URL originada al pulsar el boton 'Login'
	 * en el 'Login.jsp', devolviendo un error en caso de no coincidencia o ausencia
	 * de credenciales; y la vista principal de la app en caso de éxito.
	 * 
	 * @param model (para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @param usu   (para obtener el nombre que ha escrito el usuario en el
	 *              formulario del 'Login.jsp')
	 * @param passw (para obtener el password que ha escrito el usuario en el
	 *              formulario del 'Login.jsp')
	 * @return String con la vista 'Login' o 'Principal' en funcion de la logica de
	 *         negocio del 'usuarioService'
	 */
	@PostMapping("/login") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente. En este caso uso el metodo POST por razones de seguirdad, ya que asi no se mostraran las credenciales en la URL.
							
	public String login(Model model, @RequestParam("usuario") String usu, @RequestParam("password") String passw) {

		/*
		 * A traves del catch recogen los posibles errores con la coincidencia de las
		 * credenciales escritas por el usuario en el formulario del 'Login.jsp' y su
		 * existencia en la BBDD 'hoteldb'. Devolviendo de nuevo en este caso la vista
		 * de 'Login.jsp' con el mensaje de error correspondiente. Se devuelve la pagina
		 * 'Principal.jsp' en caso de coincidencia con la BBDD.
		 */
		try {

			usuarioService.loginUsuario(usu, passw);

		} catch (Exception e) {

			model.addAttribute("errorLogin", e.getMessage());

			return "Login";

		}

		return "Principal";
	}

}
