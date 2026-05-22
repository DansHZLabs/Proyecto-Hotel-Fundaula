package es.accenture.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Habitacion;
import es.accenture.interfaces.IHabitacionDao;

@Transactional //para que Spring gestione automáticamente las transacciones
@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd
public class HabitacionDao implements IHabitacionDao {

    private SessionFactory mySessionFactory;
    
    @Autowired
    public HabitacionDao(SessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }
	// método para obtener detalles de todas las habitaciones
	@Override
	public List<Habitacion>buscarHabitaciones() {
		
		List<Habitacion>habitaciones=null;

		Session miSession=mySessionFactory.getCurrentSession();

			habitaciones=miSession.createQuery("from Habitacion",Habitacion.class).getResultList();

		return habitaciones; // se devuelve la lista de las habitaciones

	}

	// método para hacer el alta de una habitación
	@Override
	public void guardarHabitacion(Habitacion habitacion) {

			Session miSession=mySessionFactory.getCurrentSession();
		
			miSession.save(habitacion);
		
	}

	// método para hacer modificación de una habitación
	@Override
	public void actualizarHabitacion(Habitacion habitacion) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.update(habitacion);
			
		}
		
			

	// método para eliminar una habitación
	@Override
	public void eliminarHabitacion(int idHabitacion) {

			Session miSession=mySessionFactory.getCurrentSession();
			
			//primero hay que buscar la habitación en bbdd por Id
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);

			if(habitacion!=null) {
			
				miSession.delete(habitacion);
			}
		
	}		


	// método para obtener una habitación por su Id
	@Override
	public Habitacion buscarHabitacionPorId(int idHabitacion) {

		Session miSession=mySessionFactory.getCurrentSession();
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);
			
			return habitacion;
		
	}

}
