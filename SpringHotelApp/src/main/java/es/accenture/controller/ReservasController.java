package es.accenture.controller;                        //esto entre los 2 A y B mucho cuidado

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.accenture.entity.Reserva;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.interfaces.IReservaService;

@Controller //Anotación que le dice a Spring que esta clase es un controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	private IReservaService reservaService;

	@Autowired // inyección en el constructor
	public ReservasController(IReservaService reservaService) {
	    this.reservaService = reservaService;
	}
	
	
    @GetMapping //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en reservas, se ejecuta el método listarReservas, va al service, de ahí al dao, lo saca de bbdd, lo guarda en la lista y devuelve la vista reservas con el listado
    public String obtenerReservas(Model model) {

        List<Reserva>reservas=reservaService.buscarReservas(); //llama al service de ahí al dao y a bbdd y lo guarda en la lista reservas

        model.addAttribute("reservas",reservas); //model es la caja que guarda la lista reservas en model con el nombre reservas

        return "Reservas"; //devuelve la vista jsp de reservas y muestra el listado
        
    }
	
    // método para ver el detalle de una reserva
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "reserva" y devuelve la vista detalleReserva
    public String detalleReserva(@RequestParam int id,Model model) { //se pone RequestParam para ocultar la url por contraseñas, así no usamos REST con PathVariable
    	
    	try {
    		
        Reserva reserva=reservaService.buscarReservaPorId(id); //obtiene una reserva por su Id a través del service

        model.addAttribute("reserva",reserva); //model es la caja que guarda el objeto reserva en model con el nombre reserva

        return "DetalleReserva"; //devuelve la jsp de detalle
        
    }catch(BuscarException e) {
    	
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
    
}
