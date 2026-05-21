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

@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	private IReservasService reservaService;
	
	private IHabitacionService habitacionService;
	
	private IHuespedService huespedService;

	@Autowired // inyección en el constructor
	public ReservasController(IReservasService reservaService, IHabitacionService habitacionService, IHuespedService huespedService) {
	    this.reservaService = reservaService;
	    this.habitacionService = habitacionService;
	    this.huespedService = huespedService;
	}
	
	
    @GetMapping //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en reservas, se ejecuta el método listarReservas, va al service, de ahí al dao, lo saca de bbdd, lo guarda en la lista y devuelve la vista reservas con el listado
    public String obtenerReservas(Model model) {

        List<Reserva>reservas=reservaService.buscarReservas(); //llama al service de ahí al dao y a bbdd y lo guarda en la lista reservas

        model.addAttribute("reservas",reservas); //model es la caja que guarda la lista reservas en model con el nombre reservas

        return "Reservas"; //devuelve la vista jsp de reservas y muestra el listado
        
    }
	
    // método para ver el detalle de una reserva
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "reserva" y devuelve la vista detalleReserva
    public String detalleReserva(@RequestParam int idReserva,Model model) { //se pone RequestParam para ocultar la url por contraseñas, así no usamos REST con PathVariable
    	
    	try {
    		
        Reserva reserva=reservaService.buscarReservaPorId(idReserva); //obtiene una reserva por su Id a través del service

        model.addAttribute("reserva",reserva); //model es la caja que guarda el objeto reserva en model con el nombre reserva

        return "DetalleReserva"; //devuelve la jsp de detalle
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("reservas",reservaService.buscarReservas());

		return "Reservas";
		
    	}
    
    }
    
	/**
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
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
	
    
    
    
    

    // método para eliminar reserva de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en eliminar se ejecuta el método eliminarReserva, ejecuta el método del service y lo borra de bbdd a través del dao, luego redirige a la jsp reservas y muestra el listado
    public String eliminarReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
    		reservaService.eliminarReserva(idReserva); // borra de bbdd a través del service
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage()); //crea la caja model donde se añade el error que recupera el mensaje de la excepción
    		
    		model.addAttribute("reservas",reservaService.buscarReservas()); //se vuelve a cargar la lista de reservas porque sino al salir el mensaje en rojo no aparece

    		return "Reservas"; //vuelve a la jsp reservas
    		
    	}
    		
        return "redirect:/reservas"; // Redirige a la jsp reservas y muestra el listado si todo sale bien
        
    }
    
    
	/**
	 * Metodo
	 * @param model
	 * @return
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
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
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
	 * Metodo
	 * @param huesped
	 * @param model
	 * @return
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
