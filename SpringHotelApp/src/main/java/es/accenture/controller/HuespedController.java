package es.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * @param model (para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Huespedes'
	 */
	@GetMapping("/huespedes") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String obtenerHuespedes(Model model) {
		
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
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
	 */
	@GetMapping("/detalleHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String detalleHuesped (@RequestParam("idHuesped") int idHuesped, Model model) {
		
		Huesped detalleHuesped = huespedService.buscarHuesped(idHuesped);
		
		model.addAttribute("detalleHuesped",detalleHuesped);
		
		return "DetalleHuesped";
	}
	
	
	/**
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
	 */
	@GetMapping("/modificarHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String modificarHuesped (@ModelAttribute("idHuesped") Huesped huesped, Model model) {
		
		Huesped detalleHuesped = huespedService.buscarHuesped(huesped.getIdHuesped());
		
		model.addAttribute("plantillaHuesped",detalleHuesped); //puede que tengamos conflictos aqui con el jsp de formulario ya que tiene que tener el mismo atributo para nuevo y modificar
		
		return "FormularioHuesped";
	}
	
	/**
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
	 */
	@GetMapping("/eliminarHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String eliminarHuesped (@RequestParam("idHuesped") int idHuesped, Model model) {
		
	
		try {
			
			huespedService.eliminarHuesped(idHuesped);
			
		
			
		} catch(Exception e) {
			
			model.addAttribute("errorEliminarHuesped", e); 	
			
			
			
		} 			
			return obtenerHuespedes(model);		
		
	}
	
	/**
	 * Metodo
	 * @param model
	 * @return
	 */
	@GetMapping("/nuevoHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String nuevoHuesped (Model model) {
		
		model.addAttribute("plantillaHuesped", new Huesped());
		
		return "FormularioHuesped";		
		
	}
	
	
	/**
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
	 */
	@GetMapping("/actualizarHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String actualizarHuesped (@ModelAttribute("plantillaHuesped") Huesped huesped, Model model) {	
		
		try {
			
		huespedService.actualizarHuesped(huesped);
		
		} catch (Exception e) {
			
			model.addAttribute("errorActualizarHuesped",e);
			
			return modificarHuesped(huesped, model);
		}
		
		return obtenerHuespedes(model);
	}
	
	
	/**
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
	 */
	@GetMapping("/guardarHuesped") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String guardarHuesped (@ModelAttribute("plantillaHuesped") Huesped huesped, Model model) {
		
		try {
			
			huespedService.guardarHuesped(huesped);
			
			} catch (Exception e) {
				
				model.addAttribute("errorActualizarHuesped",e);
				
				return nuevoHuesped(model); // a lo mejor aqui hay problemas porque desaparecen los datos y hay que volver a rellenarlos
			}
			
			return obtenerHuespedes(model);
		}
	
	
	
	
}
