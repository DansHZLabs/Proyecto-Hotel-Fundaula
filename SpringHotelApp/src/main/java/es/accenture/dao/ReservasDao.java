package es.accenture.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Reserva;
import es.accenture.interfaces.IReservasDao;

/**
 * Clase DAO para el acceso a la bbdd de la entidad Reserva.
 * 
 * Esta clase implementa las operaciones CRUD de las reservas.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Transactional
@Repository
public class ReservasDao implements IReservasDao{
	
	/*
	 * Atributo donde se almacena la factory y acceder a la base de datos.
	 */
	 private SessionFactory mySessionFactory;

	 /**
		 * Constructor por parametros en el que se realiza la inyeccion de
		 * dependencias de la factory.
		 * 
		 * @param mySessionFactory factoria de sesiones utilizada para acceder a bbdd
		 */
	 @Autowired
	    public ReservasDao(SessionFactory mySessionFactory) {
	        this.mySessionFactory = mySessionFactory;
	        
	    }
	 
	 /**
		 * Metodo que obtiene el listado de reservas de bbdd.
		 * 
		 * @return lista de objetos Reserva
		 */
	// método para obtener detalles de todas las reservas
	@Override
	public List<Reserva> buscarReservas() {
		
		List<Reserva>reservas=null;
		
		Session miSession=mySessionFactory.getCurrentSession();

		reservas=miSession.createQuery("from Reserva",Reserva.class).getResultList();
		
		return reservas;
		
	}

	/**
	 * Metodo que guarda una reserva en bbdd.
	 * 
	 * @param reserva objeto Reserva con la informacion a guardar.
	 */
	// método para hacer el alta de una reserva
	@Override
	public void guardarReserva(Reserva reserva) {
	
		Session miSession=mySessionFactory.getCurrentSession();
		
		miSession.save(reserva);
		
	}

	/**
	 * Metodo que actualiza la informacion de una reserva en bbdd.
	 * 
	 * @param reserva objeto Reserva con la informacion modificada
	 */
	// método para hacer modificación de una reserva
	@Override
	public void actualizarReserva(Reserva reserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		miSession.update(reserva);
		
	}

	/**
	 * Metodo que elimina una reserva de bbdd.
	 * 
	 * @param idReserva identificador de la reserva que se desea eliminar
	 */
	// método para eliminar una reserva
	@Override
	public void eliminarReserva(int idReserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		Reserva reserva=miSession.get(Reserva.class,idReserva);
		
		if(reserva!=null) {

			miSession.delete(reserva);
			
		}
	}

	/**
	 * Metodo que obtiene una reserva de bbdd por su id.
	 * 
	 * @param idReserva identificador de la reserva que se desea consultar
	 * @return objeto Reserva encontrado en la base de datos
	 */
	// método para obtener una reserva por su Id
	@Override
	public Reserva buscarReservaPorId(int idReserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		Reserva reserva=miSession.get(Reserva.class,idReserva);
		
		return reserva;
		
	}

}
