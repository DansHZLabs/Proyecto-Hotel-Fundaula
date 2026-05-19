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

@Controller //Anotación que le dice a Spring que esta clase es un controller, es como zidane reparte juego y mueve el balón, el dispatcherServlet es el entrenador que dice a que medio hay que poner en el campo, vinicius es el service porque hace el regate, luego valverde conecta con bbdd y centra y cristiano es la jsp remata y muestra el resultado
@RequestMapping("/habitaciones") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class HabitacionController {

    //@Autowired // inyección de dependencias del service para no hacer new IHabitacionService myService=new habitacionService crea el objeto automático
    //private IHabitacionService habitacionService; //se quita porque Daniel lo tiene por constructor y es mejor práctica

	private IHabitacionService habitacionService;

	@Autowired // inyección en el constructor
	public HabitacionController(IHabitacionService habitacionService) {
	    this.habitacionService = habitacionService;
	}
	
    @GetMapping //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en habitaciones, se ejecuta el método listarHabitaciones, va al service, de ahí al dao, lo saca de bbdd, lo guarda en la lista y devuelve la vista habitaciones con el listado
    public String obtenerHabitaciones(Model model) {

        List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); //llama al service de ahí al dao y a bbdd y lo guarda en la lista habitaciones

        model.addAttribute("habitaciones",habitaciones); //model es la caja que guarda la lista habitaciones en model con el nombre habitaciones, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitaciones} expression language desde la jsp

        return "Habitaciones"; //devuelve la vista jsp de habitaciones y muestra el listado
    }

    // método para ver el detalle de una habitación
    @GetMapping("/detalle") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "habitacion" y devuelve la vista detalleHabitacion
    public String detalleHabitacion(@RequestParam int id,Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como habitaciones/5 o habitaciones/4 si se cambia el valor de id por ejemplo REST
    	
    	try {
    		
        Habitacion habitacion=habitacionService.buscarHabitacionPorId(id); //obtiene una habitación por su Id a través del service

        model.addAttribute("habitacion",habitacion); //model es la caja que guarda el objeto habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "DetalleHabitacion"; //devuelve la jsp de detalle
        
    }catch(BuscarException e) {
    	
    	model.addAttribute("error", e.getMessage());

		model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());

		return "Habitaciones";
		
    	}
    
    }

    // método para mostrar el formulario de alta
    @GetMapping("/nueva") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en nueva se ejecuta el método mostrarFormularioAlta, hace la caja en model con "habitacion" y devuelve la vista formularioAltaHabitacion
    public String nuevaHabitacion(Model model) {

        model.addAttribute("habitacion",new Habitacion()); //model es la caja que guarda un objeto creado nuevo de tipo habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "FormularioHabitacion"; //devuelve el jsp del formulario, se cambia porque da problemas con editar y se hace otra jsp solo para editar
    }

    // método para guardar habitación nueva
    @PostMapping("/guardar") //Anotación que dice cuál es la url de entrada que coge el método guardarHabitacion, cuándo alguien pinche en guardar, rellena los datos del objeto habitacion con los datos del formulario gracias a modelAtribute (este no es como model que guarda datos en la mochila este los asigna como atributos del objeto) y llama al método del service y redirige a la vista habitaciones con el listado
    public String guardarHabitacion(@ModelAttribute Habitacion habitacion,Model model) { //ModelAtribute hace que Spring rellene automáticamente un objeto con los datos que vienen del formulario
    	
    try {
    	if(habitacion.getIdHabitacion() != 0){
    		
    		habitacionService.actualizarHabitacion(habitacion);//para modificar si existe porque sea distinta de 0 en la comparación
    		
    	}else {
    		
        habitacionService.guardarHabitacion(habitacion); //guarda en bbdd a través del service

    	}
    	
        return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado
    }catch(GuardarException|ActualizarException e) {
    	
    	model.addAttribute("error",e.getMessage());
    	
    	model.addAttribute("habitacion",habitacion);
    	
    	return "FormularioHabitacion";
    	
    	}
    
    }

    // método para mostrar el formulario para editar
    @GetMapping("/editar") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en editar se ejecuta el método mostrarFormularioEditar, hace la caja en model con "habitacion" y devuelve la vista formularioHabitacion
    public String editarHabitacion(@RequestParam int id,Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como habitaciones/5 o habitaciones/4 si se cambia el valor de id por ejemplo REST
    	
    	try {
    		
        // Busca la habitación existente
        Habitacion habitacion=habitacionService.buscarHabitacionPorId(id); //obtiene una habitación por su id a través del service

        // La manda al formulario ya relleno
        model.addAttribute("habitacion",habitacion); //model es la caja que guarda el objeto habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "FormularioHabitacion"; //devuelve la jsp formulario, se cambia porque da problemas con guardar y se hace otra jsp solo para editar y así para separar los caminos de guardar y editar
   
    	} catch (BuscarException e) {
    	
    	model.addAttribute("error",e.getMessage());
    	
    	return "Habitaciones";
    	
    	}
    }

    // método para actualizar habitación en bbdd             //este ya no se va a usar porque al arreglar el formulario se hace desde guardar
    //@PostMapping("/actualizar") //Anotación que dice cuál es la url de entrada que coge el método actualizarHabitacion, cuándo alguien pinche en actualizar trae a través del service el método modificarHabitacion conectando con bbdd por medio del dao y trayendo los datos de habitacion con los datos del formulario gracias a modelAtribute (este no es como model que guarda datos en la mochila este los asigna como atributos del objeto) y llama al método del service y redirige a la vista habitaciones con el listado
    //public String actualizarHabitacion(@ModelAttribute Habitacion habitacion) {

        // Actualiza en BD
        //habitacionService.modificarHabitacion(habitacion); //modifica una habitacion a través del service

        // Vuelve al listado
        //return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado
    //}

    // método para eliminar habitación de bbdd
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en eliminar se ejecuta el método eliminarHabitacion, ejecuta el método del service y lo borra de bbdd a través del dao, luego redirige a la jsp habitaciones y muestra el listado
    public String eliminarHabitacion(@RequestParam int id,Model model) { //PathVariable recoge el id de la url y se crea un objeto model de tipo Model para poder usarlo
    	try {
    		
    		habitacionService.eliminarHabitacion(id); // borra de bbdd a través del service
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage()); //crea la caja model donde se añade el error que recupera el mensaje de la excepción
    		
    		model.addAttribute("habitaciones",habitacionService.buscarHabitaciones()); //se vuelve a cargar la lista de habitaciones porque sino al salir el mensaje en rojo no aparece

    		return "Habitaciones"; //vuelve a la jsp habitaciones
    		
    	}
    		
        return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado si todo sale bien
    }
    
}