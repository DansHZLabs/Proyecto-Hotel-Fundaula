package es.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.entity.Huesped;
import es.accenture.interfaces.IHuespedService;

/**
 * Controlador que devuelve, en funcion de lo que pida la request, la vista
 * inicial de 'Huespedes' o las relativas a las acciones relacionadas con el
 * mismo
 * 
 * @author danih y javi
 * @version 1.0
 */
@RequestMapping("/huespedes")
@Controller // Anotacion de Spring para mapear la clase como controlador
public class HuespedController {

	/*
	 * Atributo donde se almacena el Servicio de huesped. Se usa la interfaz y no la
	 * clase que la implementa para que exista un mayor desacoplamiento (por si en
	 * un futuro se quiere cambiar la clase que se encarga de este servicio). Spring
	 * detecta automaticamente cual es la clase que implementa la interfaz (aunque
	 * si hay varias hay que indicar la principal con etiquetas como 'Primary' o
	 * 'Qualifier').
	 */
	private IHuespedService huespedService;
	
	
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
	public HuespedController(IHuespedService huespedService) {
		
		this.huespedService = huespedService;
	}

	/**
	 *  Metodo que recoge todas las request de la URL dirigidas a la parte de huespedes,
	 * devolviendo la pagina de inicio 'Huespedes' (el viewResolver
	 * configurado en el archivo WebConfig se encarga de añadir las anotaciones de
	 * sub ruta y la extension .jsp correspondientes)
	 *  
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Huespedes'
	 */
	@GetMapping // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String obtenerHuespedes(Model model) { // Meter url exclusiva
		
		List<Huesped> listaHuespedes = null;
		
		try {
			
			 listaHuespedes = huespedService.listarHuesped();
			
		} catch (Exception e) {
			
			model.addAttribute("errorObtenerHuespedes", e.getMessage());
		}
		
		model.addAttribute("listaHuespedes", listaHuespedes);
		
		return "Huespedes";
	}
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'detalle' del huesped, para ello
	 * se hace una busqueda a partir del id del huesped, almacenando en el Model los datos relativos a este. 
	 * Finalmente se devuelven los datos a la vista correspondiente.
	 * 
	 * @param idHuesped (numero identificador del huesped sobre el que devolver los datos)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'DetalleHuesped'
	 */
	@GetMapping("/detalle") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String detalleHuesped (@RequestParam("idHuesped") int idHuesped, Model model) {
		
		Huesped detalleHuesped = huespedService.buscarHuesped(idHuesped);
		
		model.addAttribute("detalleHuesped",detalleHuesped);
		
		return "DetalleHuesped";
	}
	
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'editar' del huesped, para ello
	 * se hace una busqueda a partir del id del huesped, almacenando en el Model los datos relativos a este. 
	 * Finalmente se devuelven los datos a la vista correspondiente, en un formato de formulario, el cual
	 * podra ser modificado por el usuario. 
	 * 
	 * @param idHuesped (numero identificador del huesped sobre el que devolver los datos)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'FormularioHuesped'
	 */
	@GetMapping("/editar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String editarHuesped (@RequestParam("idHuesped") int idHuesped, Model model) {
		
		Huesped detalleHuesped = huespedService.buscarHuesped(idHuesped);
		
		model.addAttribute("plantillaHuesped",detalleHuesped);
		
		model.addAttribute("tipoFormulario", "modificado");
		
		return "FormularioHuesped";
	}
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'eliminar' del huesped, para ello
	 * se hace una busqueda a partir del id del huesped. Posteriormente se elimina el huesped, devolviendo
	 * de nuevo la vista principal de huespedes en caso de exito, añadiendo un error en caso de fallo.
	 * 
	 * @param idHuesped (numero identificador del huesped sobre el que eliminar los datos)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista general del metodo 'obtenerHuespedes'
	 */
	@GetMapping("/eliminar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String eliminarHuesped (@RequestParam("idHuesped") int idHuesped, Model model) {
		
	
		try {
			
			huespedService.eliminarHuesped(idHuesped);		
			
		} catch(Exception e) {
			
			/* Capturamos el error definido por las reglas de negocio 
			 * en el HuespedService y lo mostramos en la vista de huespedes
			 * a modo de explicacion de que ha fallado. Aqui no hacemos
			 * una redireccion y llamamos al metodo de obtenerhuespedes
			 * debido a que sino se borrarian los datos del model, y por 
			 * lo tanto perderiamos lo almacenado en el mensaje de error */
			
			model.addAttribute("errorEliminarHuesped", e.getMessage()); // abstraccion error (pagina nueva)
			
			return obtenerHuespedes(model);				
			
		} 			
			return "redirect:/huespedes";	// constante	
		
	}
	
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'nuevo' del huesped. Se genera 
	 * una plantilla de formulario en blanco para que el usuario pueda rellenar los datos correspondientes a un nuevo
	 * huesped. 
	 * 
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * 
	 * @return String con la vista general del metodo 'FormularioHuesped'
	 */
	@GetMapping("/nuevo") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String nuevoHuesped (Model model) {
		
		/* Se crea un Huesped vacio y se almacena en el Model para que las etiquetas
		 * form del 'FormularioHuesped.jsp' puedan tener una plantilla y funcionen.
		 * Respecto al parametro nuevo selecciona la logica form del jsp
		 * adecuada para un nuevo Formulario sin datos (ya que se usa 
		 * el mismo formulario tanto para editar datos existentes como de 
		 * nuevo huesped) */
		
		model.addAttribute("plantillaHuesped", new Huesped());
		model.addAttribute("tipoFormulario", "nuevo");
		
		return "FormularioHuesped";		
		
	}
	
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'actualizar' del huesped. Obtiene
	 * los datos del formulario de huespedes rellenado por el usuario y lo actualiza en la BBDD 'hoteldb', mostrando
	 * un mensaje de error en caso de fallo.
	 * 
	 * @param huesped (objeto para almacenar los datos del huesped rellenados por el usuario en el 'FormularioHuesped.jsp'
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista general del metodo 'obtenerHuespedes'
	 */
	@PostMapping("/actualizar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String actualizarHuesped (@ModelAttribute("plantillaHuesped") Huesped huesped, Model model) {// USAR DTO para las entities (para no usarlas directamente)	
		
		try {// anotacion controllerAdvicer clase que recoge los errores y no haria falta catch
			
		huespedService.actualizarHuesped(huesped);
		
		} catch (Exception e) {
			
			/* Si se encuentra algun error segun las logicas de negocio del 
			 * huespedService, se captura con el catch y se envia 
			 * la informacion del fallo en el model, mostrando
			 * de nuevo los datos del huesped para poder corregirlos.
			 * Ademas se reenvia el tipo de formulario de nuevo para que
			 * la logica del 'FormularioHuesped.jsp' pueda elegir
			 * que form utilizar. El uso del metodo post en vez de get
			 * es porque se esta modificando el estado del servidor.  */
			
			model.addAttribute("errorActualizarHuesped",e.getMessage());
			
			model.addAttribute("plantillaHuesped", huesped);
			
			model.addAttribute("tipoFormulario", "modificado");
			
			return "FormularioHuesped";
		}
		
		return "redirect:/huespedes";
	}
	
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'guardar' del huesped. Obtiene
	 * los datos del formulario de huespedes rellenado por el usuario, crea un nuevo usario y lo guarda en la BBDD
	 * 'hoteldb', mostrando un mensaje de error en caso de fallo.
	 * 
	 * @param huesped
	 * @param model
	 * @return
	 */
	@PostMapping("/guardar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String guardarHuesped (@ModelAttribute("plantillaHuesped") Huesped huesped, Model model) {
		
		/* El uso del metodo post en vez de get
			 * es porque se esta modificando el estado del servidor. */
		try {
			
			huespedService.guardarHuesped(huesped);
			
			} catch (Exception e) {
				
				model.addAttribute("errorActualizarHuesped",e.getMessage());
				
				return nuevoHuesped(model); 
			}
			
			return "redirect:/huespedes";
		}	
		
	
}
