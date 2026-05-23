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

/**
 * Controlador encargado de gestionar las peticiones relacionadas con las habitaciones.
 * 
 * Esta clase permite hacer el crud.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Controller
@RequestMapping("/habitaciones") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class HabitacionController {

	/*
	 * Atributo donde se almacena todo el servicio de habitaciones. Se utiliza la
	 * interfaz para desacoplar (buena práctica).
	 */
	private IHabitacionService habitacionService;

	/**
	 * Constructor por parametros en el que se realiza la inyeccion de
	 * dependencias del servicio de habitaciones.
	 * 
	 * @param habitacionService servicio encargado de la logica de habitaciones
	 */
	@Autowired // inyección en el constructor
	public HabitacionController(IHabitacionService habitacionService) {
	    this.habitacionService = habitacionService;
	}
	
	/**
	 * Metodo que recoge las peticiones dirigidas a la vista de habitaciones,
	 * obtiene la lista completa de habitaciones registradas.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista 'Habitaciones'
	 */
    @GetMapping //Anotación que dice cuál es la url de entrada
    public String obtenerHabitaciones(Model model) {

        List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); //lista donde se guardará la consulta

        model.addAttribute("habitaciones",habitaciones); 

        return "Habitaciones"; 
    }

    /**
     * Metodo que recoge las peticiones de la vista de detalle de una habitacion.
     * 
     * @param id    identificador de la habitacion que se desea consultar
     * @param model objeto que permite almacenar atributos y los mensajes de error
     * @return String con la vista DetalleHabitacion o return a la lista
     */
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

    /**
     * Metodo que recoge las peticiones de la creacion de nueva habitacion, formulario vacio.
     * 
     * @param model objeto que permite almacenar atributos para enviarlos a la vista
     * @return String con la vista FormularioHabitacion
     */
    // método para mostrar el formulario de alta
    @GetMapping("/nueva") //Anotación que dice cuál es la url de entrada
    public String nuevaHabitacion(Model model) {

        model.addAttribute("habitacion",new Habitacion());

        return "FormularioHabitacion"; 
    }

    /**
     * Metodo que recoge las peticiones de guardar o actualizar una habitacion
     * a partir de los datos recibidos desde formulario.
     * 
     * @param habitacion objeto habitacion con los datos introducidos o modificados
     * @param model      objeto que permite almacenar atributos y los mensajes de error
     * @return redireccion a la vista principal o return al formulario
     */
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

    /**
     * Metodo que recoge las peticiones de edicion de una habitacion,
     * carga previamente los datos que hay.
     * 
     * @param id    identificador de la habitacion que se desea editar
     * @param model objeto que permite almacenar atributos y los mensajes de error
     * @return String con la vista FormularioHabitacion
     */
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

    /**
     * Metodo que recoge las peticiones de eliminacion de una habitacion.
     * 
     * @param id    identificador de la habitacion que se desea eliminar
     * @param model objeto que permite almacenar atributos y los mensajes de error
     * @return redireccion a la vista principal de habitaciones
     */
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