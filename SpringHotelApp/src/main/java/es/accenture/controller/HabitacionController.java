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
import es.accenture.interfaces.IHabitacionService;

@Controller //Anotación que le dice a Spring que esta clase es un controller, es como zidane reparte juego y mueve el balón, el dispatcherServlet es el entrenador que dice a que medio hay que poner en el campo, vinicius es el service porque hace el regate, luego valverde conecta con bbdd y centra y cristiano es la jsp remata y muestra el resultado
@RequestMapping("/habitaciones") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class HabitacionController {

    @Autowired // inyección de dependencias del service para no hacer new IHabitacionService myService=new habitacionService crea el objeto automático
    private IHabitacionService habitacionService;

    @GetMapping //Anotación que dice cuál es la url de entrada que coge el método, cuando alguien entre en habitaciones, se ejecuta el método listarHabitaciones, va al service, de ahí al dao, lo saca de bbdd, lo guarda en la lista y devuelve la vista habitaciones con el listado
    public String listarHabitaciones(Model model) {

        List<Habitacion> habitaciones = habitacionService.obtenerTodasHabitaciones(); //llama al service de ahí al dao y a bbdd y lo guarda en la lista habitaciones

        model.addAttribute("habitaciones", habitaciones); //model es la caja que guarda la lista habitaciones en model con el nombre habitaciones, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitaciones} expression language desde la jsp

        return "habitaciones"; //devuelve la vista jsp de habitaciones y muestra el listado
    }

    // método para ver el detalle de una habitación
    @GetMapping("/{id}") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en id se ejecuta el método verDetalle, hace la caja en model con "habitacion" y devuelve la vista detalleHabitacion
    public String verDetalle(@PathVariable int id, Model model) { //PathVariable es la anotación que recoge un valor que viene dentro de la url como habitaciones/5 o habitaciones/4 si se cambia el valor de id por ejemplo

        Habitacion habitacion = habitacionService.obtenerHabitacionPorId(id); //obtiene una habitación por su Id a través del service

        model.addAttribute("habitacion", habitacion); //model es la caja que guarda el objeto habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "detalleHabitacion"; //devuelve la jsp de detalle
    }

    // método para mostrar el formulario de alta
    @GetMapping("/nueva") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien entre en nueva se ejecuta el método mostrarFormularioAlta, hace la caja en model con "habitacion" y devuelve la vista formularioAltaHabitacion
    public String mostrarFormularioAlta(Model model) {

        model.addAttribute("habitacion", new Habitacion()); //model es la caja que guarda un objeto creado nuevo de tipo habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "formularioAltaHabitacion"; //devuelve el jsp del formulario, se cambia porque da problemas con editar y se hace otra jsp solo para editar
    }

    // método para guardar habitación nueva
    @PostMapping("/guardar") //Anotación que dice cuál es la url de entrada que coge el método guardarHabitacion, cuándo alguien pinche en guardar, rellena los datos del objeto habitacion con los datos del formulario gracias a modelAtribute (este no es como model que guarda datos en la mochila este los asigna como atributos del objeto) y llama al método del service y redirige a la vista habitaciones con el listado
    public String guardarHabitacion(@ModelAttribute Habitacion habitacion) {

        habitacionService.altaHabitacion(habitacion); //guarda en bbdd a través del service

        return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado
    }

    // método para mostrar el formulario para editar
    @GetMapping("/editar/{id}") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en editar se ejecuta el método mostrarFormularioEditar, hace la caja en model con "habitacion" y devuelve la vista formularioHabitacion
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {

        // Busca la habitación existente
        Habitacion habitacion = habitacionService.obtenerHabitacionPorId(id); //obtiene una habitación por su id a través del service

        // La manda al formulario ya relleno
        model.addAttribute("habitacion", habitacion); //model es la caja que guarda el objeto habitacion en model con el nombre habitacion, controller es repartidor, model mochila con datos, jsp pantalla que enseña datos, luego se recogen los datos con ${habitacion} expression language desde la jsp

        return "formularioEditarHabitacion"; //devuelve la jsp formulario, se cambia porque da problemas con guardar y se hace otra jsp solo para editar y así para separar los caminos de guardar y editar
    }

    // método para actualizar habitación en bbdd
    @PostMapping("/actualizar") //Anotación que dice cuál es la url de entrada que coge el método actualizarHabitacion, cuándo alguien pinche en actualizar trae a través del service el método modificarHabitacion conectando con bbdd por medio del dao y trayendo los datos de habitacion con los datos del formulario gracias a modelAtribute (este no es como model que guarda datos en la mochila este los asigna como atributos del objeto) y llama al método del service y redirige a la vista habitaciones con el listado
    public String actualizarHabitacion(@ModelAttribute Habitacion habitacion) {

        // Actualiza en BD
        habitacionService.modificarHabitacion(habitacion); //modifica una habitacion a través del service

        // Vuelve al listado
        return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado
    }

    // método para eliminar habitación de bbdd
    @GetMapping("/eliminar/{id}") //Anotación que dice cuál es la url de entrada que coge el método, cuándo alguien pinche en eliminar se ejecuta el método eliminarHabitacion, ejecuta el método del service y lo borra de bbdd a través del dao, luego redirige a la jsp habitaciones y muestra el listado
    public String eliminarHabitacion(@PathVariable int id,Model model) throws Exception{ //PathVariable recoge el id de la url y se crea un objeto model de tipo Model para poder usarlo
    	try {
    		
    		habitacionService.eliminarHabitacion(id); // borra de bbdd a través del service
    	
    	}catch(Exception e) {
    		
    		model.addAttribute("error", e.getMessage()); //crea la caja model donde se añade el error que recupera el mensaje de la excepción
    		
    		model.addAttribute("habitaciones",habitacionService.obtenerTodasHabitaciones()); //se vuelve a cargar la lista de habitaciones porque sino al salir el mensaje en rojo no aparece

    		return "habitaciones"; //vuelve a la jsp habitaciones
    		
    	}
    		
        return "redirect:/habitaciones"; // Redirige a la jsp habitaciones y muestra el listado si todo sale bien
    }
    
}