package es.accenture.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Reserva;
import es.accenture.interfaces.IReservasDao;

@Transactional
@Repository
public class ReservasDao implements IReservasDao{
	
	 private SessionFactory mySessionFactory;

	 @Autowired
	    public ReservasDao(SessionFactory mySessionFactory) {
	        this.mySessionFactory = mySessionFactory;
	        
	    }
	 
	// método para obtener detalles de todas las reservas
	@Override
	public List<Reserva> buscarReservas() {
		
		List<Reserva>reservas=null;
		
		Session miSession=mySessionFactory.getCurrentSession();

		reservas=miSession.createQuery("from Reserva",Reserva.class).getResultList();
		
		return reservas;
		
	}

	// método para hacer el alta de una reserva
	@Override
	public void guardarReserva(Reserva reserva) {
	
		Session miSession=mySessionFactory.getCurrentSession();
		
		miSession.save(reserva);
		
	}

	// método para hacer modificación de una reserva
	@Override
	public void actualizarReserva(Reserva reserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		miSession.update(reserva);
		
	}

	// método para eliminar una reserva
	@Override
	public void eliminarReserva(int idReserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		Reserva reserva=miSession.get(Reserva.class,idReserva);
		
		if(reserva!=null) {

			miSession.delete(reserva);
			
		}
	}

	// método para obtener una reserva por su Id
	@Override
	public Reserva buscarReservaPorId(int idReserva) {
		
		Session miSession=mySessionFactory.getCurrentSession();
		
		Reserva reserva=miSession.get(Reserva.class,idReserva);
		
		return reserva;
		
	}

}
