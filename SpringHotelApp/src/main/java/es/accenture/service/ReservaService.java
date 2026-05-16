package es.accenture.service;                    //esto lo hace cualquiera entero A o B

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Reserva;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IReservaDao;
import es.accenture.interfaces.IReservaService;

@Service //Anotación para decirle a Spring que es un service
public class ReservaService implements IReservaService{

	private IReservaDao reservaDao;

	@Autowired //inyección por constructor
	public ReservaService(IReservaDao reservaDao) {
		
	    this.reservaDao = reservaDao;
	    
	}
	
	//a partir de aquí hay que ir llamando a los métodos del contrato con IReservaService y meter la lógica y luego ir llamando a los que ReservaDao ha ido sobreescribiendo de IReservaDao y así sacar datos
	//método para obtener todas las reservas
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Reserva>buscarReservas() {
		// TODO Auto-generated method stub
		
		return reservaDao.buscarReservas(); //aquí se llama a dao que consulta bbdd y devuelve la lista
		
	}

    //método para el alta de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void guardarReserva(Reserva reserva)throws GuardarException {
		// TODO Auto-generated method stub
		
    	if(reserva==null){throw new GuardarException("Error al guardar la reserva");//si no hay reserva lanza excepción
    	
    	}
    	
        reservaDao.guardarReserva(reserva); //aquí se llama a dao para guardar la reserva
        
	}

    //método para la modificación de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void actualizarReserva(Reserva reserva)throws ActualizarException {
		// TODO Auto-generated method stub
    	
    	if(reserva==null){throw new ActualizarException("Error al actualizar la reserva");}//si no hay reserva lanza excepción
    	
        // aquí hay que poner la lógica
        reservaDao.actualizarReserva(reserva); //aquí se llama a dao para actualizar en la bbdd
		
	}

    @Transactional //hay que añadirlo con el import porque sino llama fuera de la transación y rompe
    //método para la eliminación de una reserva
    @Override //Anotación para sobreescribir el método de la interfaz
	public void eliminarReserva(int idReserva)throws EliminarException,BuscarException {
		// TODO Auto-generated method stub
		
    	//no eliminar si está ocupada
    	// obtener la reserva
    	Reserva reserva=reservaDao.buscarReservaPorId(idReserva);
    	
    	if(reserva==null){ //si no existe lanza excepción porque sino sale nullpointer

    		throw new BuscarException("La reserva no existe");
    		
    	}
    	
    	//para los 3 estados
    	if (reserva.getEstadoReserva()==Reserva.EstadoReserva.PENDIENTE||reserva.getEstadoReserva()==Reserva.EstadoReserva.CONFIRMADA||reserva.getEstadoReserva()==Reserva.EstadoReserva.CANCELADA) { //se comprueba que no tenga esos estados

            throw new EliminarException("La reserva no se puede eliminar");
        }
    	
    	reservaDao.eliminarReserva(idReserva); //aquí se llama a dao para borrar de la bbdd si pasa todas las reglas
    	
	}

    //método para obtener una reserva por Id
    @Override //Anotación para sobreescribir el método de la interfaz
	public Reserva buscarReservaPorId(int idReserva)throws BuscarException {
		// TODO Auto-generated method stub
    	
    	Reserva reserva=reservaDao.buscarReservaPorId(idReserva);//aquí se almacena para comprobar que exista
    	
    	if(reserva==null) {
    		
    		throw new BuscarException("La reserva no existe");
	}
    	return reserva;
        
    }
    
}
