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
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IHabitacionService;

@Controller
@RequestMapping("/habitaciones") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class HabitacionController {

	private IHabitacionService habitacionService;

	@Autowired // inyección en el constructor
	public HabitacionController(IHabitacionService habitacionService) {
	    this.habitacionService = habitacionService;
	}
	
    @GetMapping //Anotación que dice cuál es la url de entrada
    public String obtenerHabitaciones(Model model) {

        List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); //lista donde se guardará la consulta

        model.addAttribute("habitaciones",habitaciones); 

        return "Habitaciones"; 
    }

    // método para ver el detalle de una habitación
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada
    public String detalleHabitacion(@RequestParam int id,Model model) {
    	
    	try {
    		
        Habitacion habitacion=habitacionService.buscarHabitacionPorId(id); 

        model.addAttribute("habitacion",habitacion); 

        return "DetalleHabitacion"; 
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());

		return "Habitaciones";
		
    	}
    
    }

    // método para mostrar el formulario de alta
    @GetMapping("/nueva") //Anotación que dice cuál es la url de entrada
    public String nuevaHabitacion(Model model) {

        model.addAttribute("habitacion",new Habitacion());

        return "FormularioHabitacion"; 
    }

    // método para guardar habitación nueva
    @PostMapping("/guardar") //Anotación que dice cuál es la url de entrada
    public String guardarHabitacion(@ModelAttribute Habitacion habitacion,Model model) { 
    	
    try {
    	if(habitacion.getIdHabitacion() != 0){
    		
    		habitacionService.actualizarHabitacion(habitacion);
    		
    	}else {
    		
        habitacionService.guardarHabitacion(habitacion); 

    	}
    	
        return "redirect:/habitaciones"; 
    }catch(GuardarException|ActualizarException e) {
    	
    	model.addAttribute("error",e.getMessage());
    	
    	model.addAttribute("habitacion",habitacion);
    	
    	return "FormularioHabitacion";
    	
    	}
    
    }

    // método para mostrar el formulario para editar
    @GetMapping("/editar") //Anotación que dice cuál es la url de entrada
    public String editarHabitacion(@RequestParam int id,Model model) {
    	
    	try {
    		
        // Busca la habitación existente
        Habitacion habitacion=habitacionService.buscarHabitacionPorId(id);

        // La manda al formulario ya relleno
        model.addAttribute("habitacion",habitacion);

        return "FormularioHabitacion";
   
    	} catch (BuscarException e) {
    	
    	model.addAttribute("error",e.getMessage());
    	
    	return "Habitaciones";
    	
    	}
    }

    // método para eliminar habitación de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
    public String eliminarHabitacion(@RequestParam int id,Model model) {
    	try {
    		
    		habitacionService.eliminarHabitacion(id); //
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage());
    		
    		model.addAttribute("habitaciones",habitacionService.buscarHabitaciones()); //se vuelve a cargar la lista de habitaciones porque sino al salir el mensaje en rojo no aparece

    		return "Habitaciones";
    		
    	}
    		
        return "redirect:/habitaciones";
    }
    
}