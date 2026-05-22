package es.accenture.service;                  //esto lo hace el A

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Habitacion;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IHabitacionDao;
import es.accenture.interfaces.IHabitacionService;

@Service //Anotación para decirle a Spring que es un service
public class HabitacionService implements IHabitacionService{

	//@Autowired //Anotación para hacer la inyección de dependencias de HabitacionDao aquí sin hacer new HabitacionDao=habitacionDao
	//private IHabitacionDao habitacionDao; //se quita porque Daniel lo tiene por constructor y es mejor práctica
	
	private IHabitacionDao habitacionDao;

	@Autowired //inyección por constructor
	public HabitacionService(IHabitacionDao habitacionDao) {
	    this.habitacionDao = habitacionDao;
	}
	//a partir de aquí hay que ir llamando a los métodos del contrato con IHabitacionService y meter la lógica y luego ir llamando a los que HabitacionDao ha ido sobreescribiendo de IHabitacionDao y así sacar datos
	//método para obtener todas las habitaciones
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Habitacion>buscarHabitaciones(){
		// TODO Auto-generated method stub
		
		return habitacionDao.buscarHabitaciones(); //aquí se llama a dao que consulta bbdd y devuelve la lista
	}

	//método para obtener una habitación por Id
    @Override //Anotación para sobreescribir el método de la interfaz
    public Habitacion buscarHabitacionPorId(int idHabitacion)throws BuscarException {
    			
    	Habitacion habitacion=habitacionDao.buscarHabitacionPorId(idHabitacion);//aquí se almacena para comprobar que exista
    	
    	if(habitacion==null) {
    		
    		throw new BuscarException("La habitación no existe");
    	}
    	
    	return habitacion;
        //return habitacionDao.buscarHabitacionPorId(idHabitacion); //aquí se llama a dao que consulta una habitación por el parámetro id, llama 2 veces a bbdd, se cambia
    
    }
    
    
    //método para el alta de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void guardarHabitacion(Habitacion habitacion)throws GuardarException {
        // Aquí habría que poner las validaciones y lógica de negocio, los if y todo eso
    	
    	if(habitacion==null){throw new GuardarException("Error al guardar la habitación");//si no hay habitación lanza excepción
    	}
    	
       	if (habitacion.getNumeroHabitacion().isEmpty()
    				|| habitacion.getPrecioPorNoche() == null
    				|| habitacion.getPrecioPorNoche().compareTo(BigDecimal.ZERO) <= 0 // IA: le pregunto a gemini como convertir el decimal para poder limitar que el precio no sea menor o igual a cero 
    				
        			) {
    			
    			throw new GuardarException ("Debe rellenar todos los campos para guardar la habitacion.");
    		}
    	
        habitacionDao.guardarHabitacion(habitacion); //aquí se llama a dao para guardar la habitación
    }
    
    
    //método para la modificación de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void actualizarHabitacion(Habitacion habitacion)throws ActualizarException {

    	if(habitacion==null){throw new ActualizarException("Error al actualizar la habitación");}//si no hay habitación lanza excepción
    	
    	if (habitacion.getNumeroHabitacion().isEmpty()
				|| habitacion.getPrecioPorNoche() == null
				|| habitacion.getPrecioPorNoche().compareTo(BigDecimal.ZERO) <= 0 // IA: le pregunto a gemini como convertir el decimal para poder limitar que el precio no sea menor o igual a cero 
				
    			) {
			
			throw new ActualizarException ("Debe rellenar todos los campos para guardar la habitacion.");
		}
    	
        // aquí hay que poner la lógica
        habitacionDao.actualizarHabitacion(habitacion); //aquí se llama a dao para actualizar en la bbdd
    }
    
    @Transactional //hay que añadirlo con el import porque sino llama fuera de la transación y rompe
    //método para la eliminación de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void eliminarHabitacion(int id)throws EliminarException,BuscarException{ //poner la excepción en la interfaz y el controller
 	
    	//no eliminar si está ocupada
    	// obtener la habitación
    	Habitacion habitacion=habitacionDao.buscarHabitacionPorId(id);
    	
    	if(habitacion==null){ //si no existe lanza excepción porque sino sale nullpointer

    		throw new BuscarException("La habitación no existe");
    	}

    	if (habitacion.getDisponibilidad()!=Habitacion.Disponibilidad.DISPONIBLE) { //se comprueba que el estado sea disponible

            throw new EliminarException("La habitación está ocupada y no se puede eliminar");
        }
    	
    	//no eliminar si tiene incidencias
    	if(habitacion.getIncidencias()!=null&&!habitacion.getIncidencias().isEmpty()){ //se comprueba la lista que esté vacía
    		throw new EliminarException ("La habitación tiene incidencias y no se puede eliminar");
    	}
    	
    	//no eliminar si tiene reservas
    	if(habitacion.getReservas()!=null&&!habitacion.getReservas().isEmpty()){ //se comprueba la lista que esté vacía
    		throw new EliminarException ("La habitación tiene reservas y no se puede eliminar");
    	}
        
        habitacionDao.eliminarHabitacion(id); //aquí se llama a dao para borrar de la bbdd si pasa todas las reglas
        
    }
	
}
