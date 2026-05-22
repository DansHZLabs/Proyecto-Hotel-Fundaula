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
 * Controlador que devuelve, en funcion de lo que pida la request, la vista
 * inicial de 'Reservas' o las relativas a las acciones relacionadas con el
 * mismo
 * 
 * @author danih y javi
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/reservas") //Anotación que asigna una url al controller, es la ruta general y luego se especifica con getmapping para donde va
public class ReservasController {
	
	/* Atributo donde se almacena el Servicio de reservas, habitacion y huespedes 
	 * (estos dos ultimos porque interactuan con las reservas y por tanto se 
	 * necesitara dicho material para usar en algunos metodos).Se usa la interfaz 
	 * y no la clase que la implementa para que exista un mayor desacoplamiento (por
	 * si en un futuro se quiere cambiar la clase que se encarga de este servicio).
	 * Spring detecta automaticamente cual es la clase que implementa la interfaz 
	 * (aunque si hay varias hay que indicar la principal con etiquetas como 'Primary' o
	 * 'Qualifier'). */
	
	private IReservasService reservaService;
	
	private IHabitacionService habitacionService;
	
	private IHuespedService huespedService;

	/**
	 * 
	 * Constructor por parametros en el que se usa la inyeccion de dependencias del
	 * Servicio de Reserva, habitacion y huesped. Se hace aqui y no en el atributo 
	 * para que Spring cree al mismo tiempo el objeto de tipo LoginController y su dependencia
	 * IUsuarioService, evitando un NullPointerException al crear en primera
	 * instancia un IUsuarioService null
	 * 
	 * @param reservaService
	 * @param habitacionService
	 * @param huespedService
	 */
	@Autowired
	public ReservasController(IReservasService reservaService, IHabitacionService habitacionService, IHuespedService huespedService) {
	    this.reservaService = reservaService;
	    this.habitacionService = habitacionService;
	    this.huespedService = huespedService;
	}
	
	/**
	 * Metodo que recoge todas las request de la URL dirigidas a la parte de reservas,
	 * devolviendo la pagina de inicio 'Reservas' (el viewResolver
	 * configurado en el archivo WebConfig se encarga de añadir las anotaciones de
	 * sub ruta y la extension .jsp correspondientes)
	 * 
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Reservas'
	 */
    @GetMapping //Anotación que dice cuál es la url de entrada
    public String obtenerReservas(Model model) {

        List<Reserva>reservas=reservaService.buscarReservas();

        model.addAttribute("reservas",reservas);

        return "Reservas";
        
    }
	
    /**
     * Metodo que recoge todas las request de la url dirigidas a las seccion 'detalle' de reserva, para ello
	 * se hace una busqueda a partir del id de reserva, almacenando en el Model los datos relativos a esta. 
	 * Finalmente se devuelven los datos a la vista correspondiente. En caso de error se almacena el mensaje
	 * de fallo en el model junto con la datos de todas las reservas, mostrando de nuevo la vista principal 
	 * de 'Reservas.jsp'
	 * 
     * @param idReserva (numero identificador de la reserva sobre la que devolver los datos)
     * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
     * @return String con la vista 'Reservas'
     */
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
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'editar' de reserva, para ello
	 * se hace una busqueda a partir del id de la reserva, almacenando en el Model los datos relativos a este. 
	 * Finalmente se devuelven los datos a la vista correspondiente, en un formato de formulario, el cual
	 * podra ser modificado por el usuario. En caso de error se vuelve a la vista de 'Reservas' mostrando
	 * el mensaje del fallo.
	 * 
	 * @param idReserva (numero identificador de la reserva sobre el que devolver los datos)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Reservas'
	 */
	@GetMapping("/editar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String editarReserva (@RequestParam("idReserva") int idReserva, Model model) {		
	
		try {
		
		Reserva detalleReserva = reservaService.buscarReservaPorId(idReserva);
		
		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); 
		
		List<Huesped> huespedes = huespedService.listarHuesped();	
		
		/* Se hace una busqueda de las habitaciones y huespedes para
		 * que los form desplegables del 'FormularioReserva' puedan mostrar
		 * todas las opciones existentes de estos apartados. El mensaje
		 * del tipoFormulario se envia al jsp para que pueda decidir
		 * que form utilizar ya que se usa el mismo archivo para formulario
		 * de modificacion y creacion.*/
		
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
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'eliminar' de reserva, para ello
	 * se hace una busqueda a partir del id de reserva. Posteriormente se elimina el huesped, devolviendo
	 * de nuevo la vista principal de reserva en caso de exito, añadiendo un error en caso de fallo.
	 * 
	 * @param idReserva (numero identificador de la reserva sobre el que eliminar los datos)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'Reservas'
	 */
    @GetMapping("/eliminar") //Anotación que dice cuál es la url de entrada
    public String eliminarReserva(@RequestParam int idReserva,Model model) {
    	
    	try {
    		
    		reservaService.eliminarReserva(idReserva);
    		
    		/* Se usan dos alternativas de mensaje de error
    		 * ya que el service lanza errores relativos a 
    		 * la no existencia de la reserva a eliminar, o en 
    		 * caso de eixstencia los relativos al bloqueo del 
    		 * borrado por la logica de negocio */
    	
    	}catch(EliminarException | BuscarException e){
    		
    		model.addAttribute("error",e.getMessage());
    		
    		model.addAttribute("reservas",reservaService.buscarReservas());

    		return "Reservas";
    		
    	}
    		
        return "redirect:/reservas";
        
    }
    
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'nuevo' de la reserva. Se genera 
	 * una plantilla de formulario en blanco para que el usuario pueda rellenar los datos correspondientes a una nueva
	 * reserva. 
	 * 
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con la vista 'FormularioReserva'
	 */
	@GetMapping("/nuevo") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String nuevaReserva (Model model) {
		
		model.addAttribute("plantillaReserva", new Reserva());
		
		List<Habitacion>habitaciones=habitacionService.buscarHabitaciones(); 
		
		List<Huesped> huespedes = null;
		
		try {
		
		huespedes=huespedService.listarHuesped();
		
		} catch (Exception e) { // error relativo a la no existencia de huesped a listar
			
			model.addAttribute("error", e.getMessage());
			
			return "Reserva";
		}
		
		/* Lo mismo que lo comentado en el metodo de editar
		 * pero aqui guardamos una reserva vacia en la plantilla
		 * a utilizar por el formulario jsp */
		
		model.addAttribute("habitaciones",habitaciones);
		
		model.addAttribute("huespedes", huespedes);
		
		model.addAttribute("tipoFormulario", "nuevo");
		
		return "FormularioReserva";		
		
	}
    
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'actualizar' de reserva. Obtiene
	 * los datos del formulario de reservas rellenado por el usuario y lo actualiza en la BBDD 'hoteldb', mostrando
	 * un mensaje de error en caso de fallo.
	 * 
	 * @param reserva (objeto que trae los datos de reserva almacenados por el usuario en el formulario)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con vista de Reservas
	 */
	@PostMapping("/actualizar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String actualizarReserva (@ModelAttribute("plantillaReserva") Reserva reserva, Model model) {	
		
		// Se usa el metodo post ya que se esta modificando el estado del servidor
		try {
			
		reservaService.actualizarReserva(reserva);
		
		} catch (Exception e) {// en caso de error se muestran de nuevo los datos del formulario par poder corregirlos
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("plantillaReserva", reserva);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			model.addAttribute("tipoFormulario", "modificado");
			
			try {
				model.addAttribute("huespedes",huespedService.listarHuesped());
				
			} catch (Exception b) {// este error viene definido por si la lista de huespedes esta vacia
				
				model.addAttribute("error",b.getMessage());
			}		
			
			
			return "FormularioReserva";
		}
		
		// se usa redirect para limpiar la url y que no haya redundancias al usar f5/refrescar la pagina
		return "redirect:/reservas"; 
	}
	
	
	
	/**
	 * Metodo que recoge todas las request de la url dirigidas a las seccion 'guardar' de reserva. Obtiene
	 * los datos del formulario de reservas rellenado por el usuario, crea una nueva resrva y la guarda en la BBDD
	 * 'hoteldb', mostrando un mensaje de error en caso de fallo.
	 * 
	 * @param reserva (objeto que trae los datos de reserva almacenados por el usuario en el formulario)
	 * @param model (objeto para poder almacenar atributos y otra informacion entre
	 *              requests)
	 * @return String con vistas de Reservas
	 */
	@PostMapping("/guardar") // Etiqueta de Spring para mapear la request con el metodo del controlador correspondiente
	public String guardarReserva (@ModelAttribute("plantillaReserva") Reserva reserva, Model model) {	
		
		
		try {
			
		reservaService.guardarReserva(reserva);
		
		} catch (Exception e) {
			
			model.addAttribute("error",e.getMessage());
			
			model.addAttribute("plantillaReserva", reserva);
			
			model.addAttribute("habitaciones",habitacionService.buscarHabitaciones());
			
			/* misma explicacion que en guardar con la diferencia que aqui se indica al jsp que tipo
			 *  de form usar (formulario de guardar y actualizar son el mismo archivo jsp)
			 */
			
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
