package es.accenture.service;            

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Reserva;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IReservasDao;
import es.accenture.interfaces.IReservasService;

/**
 * Clase de servicio encargada de gestionar la logica de negocio de Reserva.
 * 
 * Esta clase pone las validaciones y reglas para hacer el crud y luego ir al dao.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service //Anotación para decirle a Spring que es un service
public class ReservasService implements IReservasService{

	/*
	 * Atributo donde se almacena el DAO de reservas para acceder a bbdd
	 */
	private IReservasDao reservaDao;

	/**
	 * Constructor por parametros en el que se realiza la inyeccion
	 * de dependencias del DAO de reservas.
	 * 
	 * @param reservaDao DAO encargado del acceso a datos de reservas
	 */
	@Autowired //inyección por constructor
	public ReservasService(IReservasDao reservaDao) {
		
	    this.reservaDao = reservaDao;
	    
	}

	/**
	 * Metodo encargado de obtener el listado de reservas de bbdd
	 * 
	 * @return lista de objetos Reserva
	 */
	//método para obtener todas las reservas
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Reserva>buscarReservas() {
	
		
		return reservaDao.buscarReservas(); //aquí se llama a dao que consulta bbdd y devuelve la lista
		
	}

	/**
     * Metodo encargado de guardar una reserva en bbdd
     * 
     * @param reserva objeto Reserva con la informacion a guardar
     * @throws GuardarException excepcion lanzada si los datos no son validos
     */
    //método para el alta de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void guardarReserva(Reserva reserva)throws GuardarException {
		
		
    	if(reserva==null){throw new GuardarException("Error al guardar la reserva");//si no hay reserva lanza excepción
    	
    	}
    	
    	if (reserva.getFechaEntrada() == null
				|| reserva.getFechaSalida() == null
				|| reserva.getHuesped().getIdHuesped() == 0
				|| reserva.getHabitacion().getIdHabitacion() == 0
				|| reserva.getNumeroHuespedes() <= 0
    			) {
			
			throw new GuardarException ("Debe rellenar todos los campos para guardar la reserva. LAs observaciones son opcionales y el numero de huespedes debe ser mayor que 0.");
		}
    	
        reservaDao.guardarReserva(reserva); //aquí se llama a dao para guardar la reserva
        
	}

    /**
     * Metodo encargado de actualizar una reserva en bbdd
     * 
     * @param reserva objeto Reserva con la informacion modificada
     * @throws ActualizarException excepcion lanzada si los datos no son validos
     */
    //método para la modificación de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void actualizarReserva(Reserva reserva)throws ActualizarException {
		
    	
    	if(reserva==null){throw new ActualizarException("Error al actualizar la reserva");}//si no hay reserva lanza excepción
    	
    	if (reserva.getFechaEntrada() == null
				|| reserva.getFechaSalida() == null
				|| reserva.getHuesped().getIdHuesped() == 0
				|| reserva.getHabitacion().getIdHabitacion() == 0
				|| reserva.getNumeroHuespedes() <= 0
    			) {
			
			throw new ActualizarException ("Debe rellenar todos los campos para guardar la reserva. LAs observaciones son opcionales y el numero de huespedes debe ser mayor que 0.");
		}
    	
    	
        reservaDao.actualizarReserva(reserva); //aquí se llama a dao para actualizar en la bbdd
		
	}

    /**
     * Metodo encargado de eliminar una reserva de bbdd
     * 
     * @param idReserva id de la reserva que se desea eliminar
     * @throws EliminarException excepcion lanzada si la reserva no puede eliminarse
     * @throws BuscarException excepcion lanzada si la reserva no existe
     */
    @Transactional //hay que añadirlo con el import porque sino llama fuera de la transación y rompe
    //método para la eliminación de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void eliminarReserva(int idReserva)throws EliminarException,BuscarException {
	
		
    	//no eliminar si está ocupada
    	// obtener la reserva
    	Reserva reserva=reservaDao.buscarReservaPorId(idReserva);
    	
    	if(reserva==null){ //si no existe lanza excepción porque sino sale nullpointer

    		throw new BuscarException("La reserva no existe");
    		
    	}
    	
    	//para los 3 estados
    	if (reserva.getEstadoReserva()!=Reserva.EstadoReserva.CANCELADA) { //se comprueba que no tenga esos estados

            throw new EliminarException("La reserva no se puede eliminar");
        }
    	
    	reservaDao.eliminarReserva(idReserva); //aquí se llama a dao para borrar de la bbdd si pasa todas las reglas
    	
	}

    /**
     * Metodo encargado de obtener una reserva de bbdd por el id
     * 
     * @param idReserva identificador de la reserva que se quiere consultar
     * @return objeto Reserva encontrado
     * @throws BuscarException excepcion lanzada si la reserva no existe
     */
    //método para obtener una reserva por Id
    @Override //Anotación para sobreescribir el método de la interfaz
	public Reserva buscarReservaPorId(int idReserva)throws BuscarException {

    	
    	Reserva reserva=reservaDao.buscarReservaPorId(idReserva);//aquí se almacena para comprobar que exista
    	
    	if(reserva==null) {
    		
    		throw new BuscarException("La reserva no existe");
	}
    	return reserva;
        
    }
    
}

