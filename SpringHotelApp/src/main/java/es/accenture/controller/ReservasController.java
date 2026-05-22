package es.accenture.controller;                        //esto entre los 2 A y B mucho cuidado

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
import es.accenture.entity.Huesped;
import es.accenture.entity.Reserva;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.interfaces.IHabitacionService;
import es.accenture.interfaces.IHuespedService;
import es.accenture.interfaces.IReservasService;

/**
 * Controlador encargado de gestionar las peticiones relacionadas con las reservas.
 * 
 * Esta clase permite hacer el crud de reservas, asi como gestionar las relaciones
 * entre huespedes y habitaciones.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	/*
	 * Atributo donde se almacena el servicio de reservas encargado de la
	 * logica relacionada con reservas.
	 */
	private IReservasService reservaService;
	
	/*
	 * Atributo donde se almacena el servicio de habitaciones
	 * para obtener informacion de las habitaciones.
	 */
	private IHabitacionService habitacionService;
	
	/*
	 * Atributo donde se almacena el servicio de huespedes
	 * para obtener informacion de los huespedes que hay.
	 */
	private IHuespedService huespedService;

	/**
	 * Constructor por parametros en el que se realiza la inyeccion de dependencias.
	 * 
	 * @param reservaService     servicio encargado de la logica de reservas
	 * @param habitacionService  servicio encargado de la logica de habitaciones
	 * @param huespedService     servicio encargado de la logica de huespedes
	 */
	@Autowired
	public ReservasController(IReservasService reservaService, IHabitacionService habitacionService, IHuespedService huespedService) {
	    this.reservaService = reservaService;
	    this.habitacionService = habitacionService;
	    this.huespedService = huespedService;
	}
	
	/**
	 * Metodo que recoge las peticiones de la vista principal de reservas
	 * obteniene el listado de reservas que hay.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista 'Reservas'
	 */
    @GetMapping //Anotación que dice cuál es la url de entrada
    public String obtenerReservas(Model model) {

        List<Reserva>reservas=reservaService.buscarReservas();

        model.addAttribute("reservas",reservas);

        return "Reservas";
        
    }
	
    /**
     * Metodo que recoge las peticiones de la vista de detalle de una reserva.
     * 
     * @param idReserva identificador de la reserva que se desea consultar
     * @param model     objeto que permite almacenar atributos y los mensajes de error
     * @return String con la vista DetalleReserva o return a la lista
     */
    // método para ver el detalle de una reserva
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada
    public String detalleReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
        Reserva reserva=reservaService.buscarReservaPorId(idReserva);

        model.addAttribute("reserva",reserva);

        return "DetalleReserva";
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("reservas",reservaService.buscarReservas());

		return "Reservas";
		
    	}
    
    }
    
	/**
	 * Metodo que recoge las peticiones de editar una reserva, carga previamente
	 * los datos quehay junto con las habitaciones y huespedes.
	 * 
	 * @param idReserva identificador de la reserva que se desea editar
	 * @param model     objeto que permite almacenar atributos y los mensajes de error
	 * @return String con la vista FormularioReserva
	 */
	@GetMapping("/editar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String editarReserva (@RequestParam("idReserva") int idReserva, Model model) {		
	
		try {
		
		Reserva detalleReserva = reservaService.buscarReservaPorId(idReserva);
		
		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); 
		
		List<Huesped> huespedes = null;		
		
		huespedes=huespedService.listarHuesped();		
		
		model.addAttribute("habitaciones",habitaciones);		
		model.addAttribute("huespedes", huespedes);		
		model.addAttribute("plantillaReserva",detalleReserva); 
		model.addAttribute("tipoFormulario", "modificado");			
		
		return "FormularioReserva";
		
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());

			model.addAttribute("reservas",reservaService.buscarReservas());
			
			return "Reservas";
		}
		
		}   

	  /**
     * Metodo que recoge las peticiones de eliminacion de una reserva.
     * 
     * @param idReserva identificador de la reserva que se quiere eliminar
     * @param model     objeto que permite almacenar atributos y losmensajes de error
     * @return redireccion a la vista principal de reservas
     */
    // método para eliminar reserva de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
    public String eliminarReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
    		reservaService.eliminarReserva(idReserva);
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage());
    		
    		model.addAttribute("reservas",reservaService.buscarReservas());

    		return "Reservas";
    		
    	}
    		
        return "redirect:/reservas";
        
    }
    
    /**
	 * Metodo que recoge las peticiones de creacion de una nueva reserva, prepara
	 *  el formulario y carga habitaciones y huespedes.
	 * 
	 * @param model objeto que permite almacenar atributos para enviarlos a la vista
	 * @return String con la vista FormularioReserva
	 */
	@GetMapping("/nuevo") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String nuevaReserva (Model model) {
		
		model.addAttribute("plantillaReserva", new Reserva());
		
		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); 
		
		List<Huesped> huespedes = null;
		
		try {
		
		huespedes=huespedService.listarHuesped();
		
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
			
			return "Reserva";
		}
		
		model.addAttribute("habitaciones",habitaciones);
		
		model.addAttribute("huespedes", huespedes);
		
		model.addAttribute("tipoFormulario", "nuevo");
		
		return "FormularioReserva";		
		
	}
    
	/**
	 * Metodo que recoge las peticiones de actualizacion de una reserva a partir
	 * de los datos recibidos desde el formulario.
	 * 
	 * @param reserva objeto reserva con la informacion modificada
	 * @param model   objeto que permite almacenar atributos y los mensajes de error
	 * @return redireccion a la vista principal o return al formulario
	 */
	@PostMapping("/actualizar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String actualizarReserva (@ModelAttribute("plantillaReserva") Reserva reserva, Model model) {	
		
		
		try {
			
		reservaService.actualizarReserva(reserva);
		
		} catch (Exception e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("plantillaReserva", reserva);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			model.addAttribute("tipoFormulario", "modificado");
			
			try {
				model.addAttribute("huespedes",huespedService.listarHuesped());
				
			} catch (Exception b) {
				
				model.addAttribute("error",b.getMessage());
			}		
			
			
			return "FormularioReserva";
		}
		
		return "redirect:/reservas";
	}
	
	/**
	 * Metodo que recoge las peticiones de guardar reserva a partir de los datos
	 * de el formulario.
	 * 
	 * @param reserva objeto reserva con la informacion introducida
	 * @param model   objeto que permite almacenar atributos y los mensajes de error
	 * @return redireccion a la vista principal o return al formulario
	 */
	@PostMapping("/guardar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String guardarReserva (@ModelAttribute("plantillaReserva") Reserva reserva, Model model) {	
		
		
		try {
			
		reservaService.guardarReserva(reserva);
		
		} catch (Exception e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("plantillaReserva", reserva);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			model.addAttribute("tipoFormulario", "nuevo");
			
			try {
				model.addAttribute("huespedes",huespedService.listarHuesped());
				
			} catch (Exception b) {
				
				model.addAttribute("error",b.getMessage());
			}		
			
			
			return "FormularioReserva";
		}
		
		return "redirect:/reservas";
	}	
    
}
