package es.accenture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.accenture.entity.Habitacion;
import es.accenture.entity.Incidencia;
import es.accenture.interfaces.IHabitacionService;
import es.accenture.interfaces.IIncidenciaService;

@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/incidencias") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class IncidenciaController {

	@Autowired //inyección de dependencias del service para no hacer new IIncidenciaService myIncidencia=new incidenciaService
	private IIncidenciaService incidenciaService;

	@Autowired //inyección de dependencias del service de habitaciones para no hacer new IHabitacionService myService=new habitacionService
	private IHabitacionService habitacionService;

	// método para listar incidencias
	@GetMapping //Anotación que dice cuál es la url de entrada que coge el método como va también a /incidencias y ya está en la general del requestMapping no se pone porque sino quedaría /incidencias/incidencias y no estaría bien
	public String listarIncidencias(Model model) {

		List<Incidencia>incidencias=incidenciaService.obtenerTodasIncidencias(); //llama al service de ahí al dao y a bbdd y lo guarda en esta lista incidencias

		model.addAttribute("incidencias",incidencias); //model es la caja que guarda la lista incidencias en model con el nombre incidencias y luego desde la jsp se recoge con ${expresion language}

		return "incidencias"; //devuelve la vista jsp incidencias y muestra el listado
	}

	// método para ver el detalle de una incidencia
	@GetMapping("/{id}") //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "incidencia" y devuelve la vista detalleIncidencia
	public String verDetalle(@PathVariable int id,Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como incidencias/5 o incidencias/4 si se cambia el valor de id por ejemplo REST

		Incidencia incidencia=incidenciaService.obtenerIncidenciaPorId(id); //obtiene una incidencia por su Id a través del service que la coge del dao que mira en bbdd

		model.addAttribute("incidencia",incidencia); //model es la caja que guarda el objeto incidencia en model con el nombre incidencia, luego se recupera desde la jsp con ${expresion language}

		return "detalleIncidencia"; //devuelve la jsp de detalleIncidencia
	}

	// método para mostrar el formulario de alta
	@GetMapping("/nueva") //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en nueva se ejecuta el método mostrarFormularioAlta, hace la caja en model con "habitaciones" y con "incidencias" que es la lista donde se guardan y lo muestra devolviendo la vista
	public String mostrarFormularioAlta(Model model) {

		model.addAttribute("incidencia",new Incidencia()); //model es la caja que guarda un objeto creado nuevo de tipo incidencia en model con el nombre incidencia

		List<Habitacion>habitaciones=habitacionService.obtenerTodasHabitaciones(); //obtiene todas las habitaciones para mostrarlas en el select

		model.addAttribute("habitaciones",habitaciones); //guarda la lista habitaciones en model para usarla en el formulario

		return "formularioAltaIncidencia"; //devuelve el jsp del formularioAltaIncidencia
	}

	// método para guardar incidencia nueva
	@PostMapping("/guardar") //Anotación que dice cuál es la url de entrada que coge el método guardarIncidencia
	public String guardarIncidencia(@ModelAttribute Incidencia incidencia) { //ModelAtribute hace que Spring rellene automáticamente un objeto con los datos que vienen del formulario

		incidenciaService.altaIncidencia(incidencia); //guarda en bbdd a través del service que las coge del dao que las guarda en bbdd

		return "redirect:/incidencias"; // Redirige a la jsp incidencias y muestra el listado
	}

	// método para mostrar el formulario para editar
	@GetMapping("/editar/{id}") //Anotación que dice cuál es la url de entrada que coge el método
	public String mostrarFormularioEditar(@PathVariable int id,Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como incidencias/5 o incidencias/4 si se cambia el valor de id por ejemplo REST

		Incidencia incidencia=incidenciaService.obtenerIncidenciaPorId(id); //obtiene una incidencia por su id a través del service,dao,bbdd

		model.addAttribute("incidencia",incidencia); //model es la caja que guarda el objeto incidencia en model con el nombre incidencia, luego desde la jsp se recoge con ${expression language}

		List<Habitacion>habitaciones=habitacionService.obtenerTodasHabitaciones(); //obtiene todas las habitaciones y las guarda en la lista habitaciones

		model.addAttribute("habitaciones",habitaciones); //guarda la lista habitaciones en model para usarla en el formulario, luego desde jsp se cogen con ${expresion languanges}

		return "formularioEditarIncidencia"; //devuelve la jsp formularioEditarIncidencia
	}

	// método para actualizar incidencia en bbdd
	@PostMapping("/actualizar") //Anotación que dice cuál es la url de entrada que coge el método actualizarIncidencia
	public String actualizarIncidencia(@ModelAttribute Incidencia incidencia) { //ModelAtribute hace que Spring rellene automáticamente un objeto con los datos que vienen del formulario

		incidenciaService.modificarIncidencia(incidencia); //modifica una incidencia a través del service,dao,bbdd

		return "redirect:/incidencias"; // Redirige a la jsp incidencias y muestra el listado de incidencias
	}

	// método para eliminar incidencia de bbdd
	@GetMapping("/eliminar/{id}") //Anotación que dice cuál es la url de entrada que coge el método
	public String eliminarIncidencia(@PathVariable int id,Model model)throws Exception{ //PathVariable es la anotación que recoge un valor que viene dentro de la url como incidencias/5 o incidencias/4 si se cambia el valor de id por ejemplo REST

		try {

			incidenciaService.eliminarIncidencia(id); // borra de bbdd a través del service,dao,bbdd

		}catch(Exception e){

			model.addAttribute("error",e.getMessage()); //crea la caja model, ahí se añade el error que recupera el mensaje de la excepción

			model.addAttribute("incidencias",incidenciaService.obtenerTodasIncidencias()); //se vuelve a cargar la lista de incidencias porque sino al salir el mensaje en rojo no aparece

			return "incidencias"; //vuelve a la jsp incidencias y la muestra
		}

		return "redirect:/incidencias"; // Redirige a la jsp incidencias y muestra el listado si todo sale bien
	}

	// método para obtener incidencias por el id de una habitación
	@GetMapping("/habitacion/{idHabitacion}") //Anotación que dice cuál es la url de entrada que coge el método
	public String obtenerIncidenciasPorHabitacion(@PathVariable int idHabitacion, Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como incidencias/5 o incidenciass/4 si se cambia el valor de id por ejemplo REST

		List<Incidencia>incidencias=incidenciaService.obtenerIncidenciasPorIdHabitacion(idHabitacion); //obtiene las incidencias de una habitación y las guarda en la lista incidencias

		Habitacion habitacion=habitacionService.obtenerHabitacionPorId(idHabitacion); //obtiene la habitación por su id y la guarda en habitacion, service,dao,bbdd

		model.addAttribute("incidencias",incidencias); //guarda la lista incidencias en model, luego se cogerán en jsp con ${expresion language}

		model.addAttribute("habitacion",habitacion); //guarda la habitación en model, luego se cogerán en jsp con ${expresion language}

		return "incidencias"; //devuelve la jsp incidencias y muestra las de esa habitación
	}

}