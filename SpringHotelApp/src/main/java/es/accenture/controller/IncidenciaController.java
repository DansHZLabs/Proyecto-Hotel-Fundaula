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

import es.accenture.entity.Habitacion;
import es.accenture.entity.Incidencia;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IHabitacionService;
import es.accenture.interfaces.IIncidenciaService;

@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/incidencias") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class IncidenciaController {

	@Autowired
	private IIncidenciaService incidenciaService;

	@Autowired
	private IHabitacionService habitacionService;

	// método para listar incidencias
	@GetMapping //Anotación que dice cuál es la url de entrada
	public String obtenerIncidencias(Model model) {

		List<Incidencia>incidencias=incidenciaService.buscarTodasIncidencias();

		model.addAttribute("incidencias",incidencias); 

		return "Incidencias"; 
	}

	// método para ver el detalle de una incidencia
	@GetMapping("/detalle") //Anotación que dice cuál es la url de entrada
	public String detalleIncidencia(@RequestParam int id,Model model) { 
		
		try {
			
		Incidencia incidencia=incidenciaService.buscarIncidenciaPorId(id); 

		model.addAttribute("incidencia",incidencia); 

		return "DetalleIncidencia"; 
		
		}catch(BuscarException e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias());
			
			return "Incidencias";
			
		}

	}
	
	// método para mostrar el formulario de alta
	@GetMapping("/nueva") //Anotación que dice cuál es la url de entrada
	public String nuevaIncidencia(Model model) {

		model.addAttribute("incidencia",new Incidencia()); 

		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones();

		model.addAttribute("habitaciones",habitaciones); 

		return "FormularioIncidencia";
	}

	// método para guardar incidencia nueva
	@PostMapping("/guardar") //Anotación que dice cuál es la url de entrada
	public String guardarIncidencia(@ModelAttribute Incidencia incidencia,Model model) {
	
	try {
		if(incidencia.getIdIncidencia()!=0){
					
				incidenciaService.actualizarIncidencia(incidencia);
			
		}else {
			
			incidenciaService.guardarIncidencia(incidencia);
			
		}

		
	}catch(GuardarException|ActualizarException e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("incidencia",incidencia);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			return "FormularioIncidencia";
			
		}
		
		return "redirect:/incidencias";
		
	}

	// método para mostrar el formulario para editar
	@GetMapping("/editar") //Anotación que dice cuál es la url de entrada
	public String editarIncidencia(@RequestParam int id,Model model) {

		try {
			
		Incidencia incidencia=incidenciaService.buscarIncidenciaPorId(id);

		model.addAttribute("incidencia",incidencia);

		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones();

		model.addAttribute("habitaciones",habitaciones);

		return "FormularioIncidencia";
		
		}catch(BuscarException e) {
			
			model.addAttribute("error",e.getMessage());

			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias());

			return "Incidencias";
			
		}
		
	}

	// método para eliminar incidencia de bbdd
	@GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
	public String eliminarIncidencia(@RequestParam int id,Model model){

		try {

			incidenciaService.eliminarIncidencia(id);

		}catch(EliminarException |BuscarException e){

			model.addAttribute("error",e.getMessage());

			model.addAttribute("incidencias",incidenciaService.buscarTodasIncidencias()); //se vuelve a cargar la lista de incidencias porque sino al salir el mensaje en rojo no aparece

			return "Incidencias";
		}

		return "redirect:/incidencias";
	}

	// método para obtener incidencias por el id de una habitación
	@GetMapping("/habitacion") //Anotación que dice cuál es la url de entrada
	public String obtenerIncidenciasPorHabitacion(@RequestParam int idHabitacion, Model model) {
		
		try {
			
		List<Incidencia>incidencias=incidenciaService.buscarIncidenciasPorIdHabitacion(idHabitacion);

		Habitacion habitacion=habitacionService.buscarHabitacionPorId(idHabitacion);

		model.addAttribute("incidencias",incidencias);

		model.addAttribute("habitacion",habitacion);

		return "Incidencias";
		
		}catch (BuscarException e) {
			
			model.addAttribute("error", e.getMessage());

			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
		
			return "Habitaciones";
			
		}
		
	}
	
}