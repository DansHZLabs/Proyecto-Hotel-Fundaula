package es.accenture.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.accenture.entity.Usuario;
import es.accenture.interfaces.IUsuarioService;

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
	 * Atributo donde se almacena el Servicio de usuario. Se usa la interfaz y no la
	 * clase que la implementa para que exista un mayor desacoplamiento (por si en
	 * un futuro se quiere cambiar la clase que se encarga de este servicio). Spring
	 * detecta automaticamente cual es la clase que implementa la interfaz (aunque
	 * si hay varias hay que indicar la principal con etiquetas como 'Primary' o
	 * 'Qualifier').
	 */
	private IUsuarioService usuarioService;

	/**
	 * Constructor por parametros en el que se usa la inyeccion de dependencias del
	 * Servicio de Usuario. Se hace aqui y no en el atributo para que Spring cree al
	 * mismo tiempo el objeto de tipo LoginController y su dependencia
	 * IUsuarioService, evitando un NullPointerException al crear en primera
	 * instancia un IUsuarioService null
	 * 
	 * @param usuarioService
	 */
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.
	public LoginController(IUsuarioService usuarioService) {

		this.usuarioService = usuarioService;
	}

	/**
	 * Metodo que recoge todas las request de la URL dirigidas a la raiz del
	 * proyecto, devolviendo la pagina de inicio 'Login' (el viewResolver
	 * configurado en el archivo WebConfig se encarga de añadir las anotaciones de
	 * sub ruta y la extension .jsp correspondientes)
	 * 
	 * @param model (para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Login'
	 */
	@GetMapping("/") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String mostrarLogin(Model model) {

		/*
		 * Le pide a Spring el objeto Model, creando una plantilla con el atributo
		 * 'credencialesLogin' a utilizar por las etiquetas form MVC del Login.jsp
		 */
		model.addAttribute("credencialesLogin", new Usuario());

		System.out.println("Entrando al login");

		return "forward:/Login.jsp"; //IA: al meter el 'Login.jsp' fuera de web-inf le pregunto a Gemini como saltarme el viewResolver configurado (anade la ruta desde web inf + extension jsp) y poder enlazar a este de manera excepcional
	}

	/**
	 * Metodo que recoge la request de la URL originada al pulsar el boton 'Login'
	 * en el 'Login.jsp', devolviendo un error en caso de no coincidencia o ausencia
	 * de credenciales; y la vista principal de la app en caso de exito.
	 * 
	 * @param usuario (objeto que se utiliza en conjunto con la etiqueta de
	 *                'ModelAttribute' de Spring para inyectar en sus parametros el
	 *                nombre y password que ha escrito el usuario en el formulario
	 *                del 'Login.jsp')
	 * @param model   (para poder almacenar atributos y otra informacion entre
	 *                requests)
	 * @return String con la vista 'Login' o 'Principal' en funcion de la logica de
	 *         negocio del 'UsuarioService'
	 */
	@PostMapping("/login") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente. En este caso uso el metodo POST por razones de seguridad, ya que asi no se mostraran las credenciales en la URL.
	public String login(@ModelAttribute("credencialesLogin") Usuario usuario, Model model) {

		/*
		 * A traves del catch recogen los posibles errores con la coincidencia de las
		 * credenciales escritas por el usuario en el formulario del 'Login.jsp' y su
		 * existencia en la BBDD 'hoteldb'. Devolviendo de nuevo en este caso la vista
		 * de 'Login.jsp' con el mensaje de error correspondiente (almacenado en el
		 * model). Se devuelve la pagina 'Principal.jsp' en caso de coincidencia con la
		 * BBDD. Se usa el post en vez de get, por dos motivos: debido a que se 
		 * esta modificando el estado de la app (se almacena en el service
		 * las credenciales en el sesion) y para que las credenciales
		 * no se muestren en la url. 
		 */
		try {

			/* A partir del objeto usuario almacenado en el model, le pasamos al Service las
			credenciales obtenidas del formulario */
			usuarioService.loginUsuario(usuario.getUsername(), usuario.getPassword());

		} catch (Exception e) {

			model.addAttribute("errorLogin", e.getMessage());
			/*
			 * Se limpia el model con las credenciales en caso de error, ya que sino nos
			 * toca borrarlo manualmente de nuevo en el formulario
			 */
			model.addAttribute("credencialesLogin", new Usuario());

			return "forward:Login.jsp"; //IA: al meter el 'Login.jsp' fuera de web-inf le pregunto a Gemini como saltarme el viewResolver configurado (anade la ruta desde web inf + extension jsp) y poder enlazar a este de manera excepcional

		}	

		return "redirect:/vueltaPrincipal"; //IA: investigo con Gemini que metodo es mas correcto (redirect o forward) para limpiar la url y que no de errores al hacer f5
	}

	
	/**
	 * Metodo que cierra la sesion del usuario, volviendo a la pagina de inicio de introduccion de credenciales. 
	 * 
	 * @param model (para poder almacenar atributos y otra informacion entre
	 *                requests)
	 * @param sesion (para poder invalidar/eliminar la sesion actual)
	 * 
	 * @return redirect al metodo 'mostrarLogin' (devuelve la vista 'Login')
	 */
	@PostMapping("/cerrarSesion") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente. En este caso uso el metodo POST por razones de seguridad.
	public String cerrarSesion (Model model, HttpSession sesion) {
		
		sesion.invalidate(); // Se usa el metodo post porque se modifica el estado de la app, eliminando las credenciales del objeto sesion.
		
		return "redirect:/"; // IA: investigo con gemini el mejor metodo para que no aparezca el /cerrar sesion otra vez en la url y no de errores al pulsar f5
		
	}
	
	/**
	 * Metodo que devuelve la vista 'Principal' de la app, mostrando tanto las crendenciales 
	 * del usuario y sus permisos, como un menu de opciones de navigacion a las distintas
	 * funcionalidades de la app.
	 * 
	 * @return String con la vista 'Principal.jsp'
	 */
	@GetMapping("/vueltaPrincipal")
	public String vueltaPrincipal () {
		
		return "Principal";
	}
	
	
	
}
