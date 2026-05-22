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

/**
 * Clase de servicio encargada de la logica de negocio de habitación
 * 
 * Esta clase pone las validaciones y reglas necesarias antes
 * de realizar operaciones sobre las habitaciones en bbdd
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service //Anotación para decirle a Spring que es un service
public class HabitacionService implements IHabitacionService{

	/*
	 * Atributo donde se almacena el DAO de habitaciones encargado del acceso a bbdd
	 */
	private IHabitacionDao habitacionDao;

	/**
	 * Constructor por parametros en el que se realiza la inyeccion
	 * de dependencias del DAO de habitaciones.
	 * 
	 * @param habitacionDao DAO encargado del acceso a datos de habitaciones
	 */
	@Autowired //inyección por constructor
	public HabitacionService(IHabitacionDao habitacionDao) {
	    this.habitacionDao = habitacionDao;
	}

	/**
	 * Metodo encargado de obtener el listado de habitaciones de bbdd
	 * 
	 * @return lista de objetos Habitacion
	 */
	//método para obtener todas las habitaciones
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Habitacion>buscarHabitaciones(){
		// TODO Auto-generated method stub
		
		return habitacionDao.buscarHabitaciones(); //aquí se llama a dao que consulta bbdd y devuelve la lista
	}

	/**
	 * Metodo encargado de obtener una habitacion de bbdd por su id
	 * 
	 * @param idHabitacion id de la habitacion que se quiere consultar
	 * @return objeto Habitacion encontrado
	 * @throws BuscarException excepcion lanzada si la habitacion no existe
	 */
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
    
    /**
     * Metodo encargado de guardar una habitacion en bbdd
     * 
     * @param habitacion objeto Habitacion con la informacion a guardar
     * @throws GuardarException excepcion lanzada si los datos no son validos
     */
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
    
    /**
     * Metodo encargado de actualizar una habitacion de bbdd
     * 
     * @param habitacion objeto Habitacion con la informacion modificada
     * @throws ActualizarException excepcion lanzada si los datos no son validos
     */
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
    	
        habitacionDao.actualizarHabitacion(habitacion); //aquí se llama a dao para actualizar en la bbdd
    }
    
    /**
     * Metodo encargado de eliminar una habitacion de bbdd por su id
     * 
     * @param id identificador de la habitacion que se quiere eliminar
     * @throws EliminarException excepcion lanzada si la habitacion no puede eliminarse
     * @throws BuscarException excepcion lanzada si la habitacion no existe
     */
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
