package es.accenture.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Habitacion;
import es.accenture.interfaces.IHabitacionDao;

/**
 * Clase DAO encargada de gestionar el acceso a bbdd
 * 
 * Esta clase implementa las operaciones CRUD de las habitaciones
 * 
 * @author danih y javi
 * @version 1.0
 */
@Transactional //para que Spring gestione automáticamente las transacciones
@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd
public class HabitacionDao implements IHabitacionDao {

	/*
	 * Atributo donde se almacena la factory de sesiones  y acceder a bbdd.
	 */
    private SessionFactory mySessionFactory;
    
    /**
     * Constructor por parametros en el que se realiza la inyeccion de
     * dependencias de la factory de sesiones.
     * 
     * @param mySessionFactory factory de sesiones utilizada para acceder a bbdd
     */
    @Autowired
    public HabitacionDao(SessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }
    
    /**
	 * Metodo que obtiene el listado de habitaciones
	 * 
	 * @return lista de los objetos Habitacion
	 */
	// método para obtener detalles de todas las habitaciones
	@Override
	public List<Habitacion>buscarHabitaciones() {
		
		List<Habitacion>habitaciones=null;

		Session miSession=mySessionFactory.getCurrentSession();

			habitaciones=miSession.createQuery("from Habitacion",Habitacion.class).getResultList();

		return habitaciones; // se devuelve la lista de las habitaciones

	}

	/**
	 * Metodo que guarda una habitacion en bbdd
	 * 
	 * @param habitacion objeto Habitacion con la informacion a guardar
	 */
	// método para hacer el alta de una habitación
	@Override
	public void guardarHabitacion(Habitacion habitacion) {

			Session miSession=mySessionFactory.getCurrentSession();
		
			miSession.save(habitacion);
		
	}

	/**
	 * Metodo que actualiza la informacion de una habitacion en bbdd
	 * 
	 * @param habitacion objeto Habitacion con la informacion modificada
	 */
	// método para hacer modificación de una habitación
	@Override
	public void actualizarHabitacion(Habitacion habitacion) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.update(habitacion);
			
		}
		
	/**
	 * Metodo que elimina una habitacion de bbdd
	 * 
	 * @param idHabitacion identificador de la habitacion que se quiere eliminar
	 */		
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

	/**
	 * Metodo que busca una habitacion en bbdd por el id
	 * 
	 * @param idHabitacion identificador de la habitacion que se quiere consultar
	 * @return objeto Habitacion encontrado en la base de datos
	 */
	// método para obtener una habitación por su Id
	@Override
	public Habitacion buscarHabitacionPorId(int idHabitacion) {

		Session miSession=mySessionFactory.getCurrentSession();
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);
			
			return habitacion;
		
	}

}
