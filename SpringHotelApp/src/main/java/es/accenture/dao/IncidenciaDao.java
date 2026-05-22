package es.accenture.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.accenture.entity.Habitacion;
import es.accenture.entity.Incidencia;
import es.accenture.interfaces.IIncidenciaDao;

/**
 * Clase DAO encargada de gestionar el acceso a bbdd
 * 
 * Esta clase implementa las operaciones CRUD relacionadas con las incidencias.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Transactional //para que Spring gestione automáticamente las transacciones
@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd
public class IncidenciaDao implements IIncidenciaDao {

	/*
	 * Atributo donde se almacena la factory de sesiones y acceder a la bbdd.
	 */
    private SessionFactory mySessionFactory;

    /**
     * Constructor por parametros en el que se realiza la inyeccion de
     * dependencias de la factory de sesiones.
     * 
     * @param mySessionFactory factory de sesiones utilizada para acceder a bbdd
     */
    @Autowired
    public IncidenciaDao(SessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }
    
    /**
     * Metodo que obtiene el listado completo de incidencias de bbdd.
     * 
     * @return lista de objetos Incidencia
     */
    // método para obtener detalles de todas las incidencias
	@Override
	public List<Incidencia>buscarIncidencias() {
		
		List<Incidencia>incidencias=null;
		
			Session miSession=mySessionFactory.getCurrentSession();

			incidencias = miSession.createQuery("from Incidencia",Incidencia.class).getResultList();

		return incidencias;

	}
	
	/**
	 * Metodo que guarda una incidencia en bbdd.
	 * 
	 * @param incidencia objeto Incidencia con la informacion a guardar
	 */
	// método para hacer el alta de una incidencia
	@Override
	public void guardarIncidencia(Incidencia incidencia) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.save(incidencia);
		
	}

	/**
	 * Metodo que actualiza la informacion de una incidencia en bbdd.
	 * 
	 * @param incidencia objeto Incidencia con la informacion modificada
	 */
	// método para hacer modificación de una incidencia
	@Override
	public void actualizarIncidencia(Incidencia incidencia) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.update(incidencia);
			
	}	
	
	/**
	 * Metodo que elimina una incidencia de bbdd.
	 * 
	 * @param idIncidencia identificador de la incidencia que se desea eliminar
	 */
	// método para eliminar una incidencia
	@Override
	public void eliminarIncidencia(int idIncidencia) { //void que no devuelve nada, solo borra

			Session miSession=mySessionFactory.getCurrentSession();

			Incidencia incidencia=miSession.get(Incidencia.class,idIncidencia);

			if(incidencia!=null) {

				miSession.delete(incidencia);
			}
	
	}		

	/**
	 * Metodo que obtiene todas las incidencias de una habitacion.
	 * 
	 * @param idHabitacion identificador de la habitacion cuyas incidencias se quiere consultar
	 * @return lista de objetos Incidencia de la habitacion
	 */
	 // método para obtener las incidencias de una habitación por su Id
	@Override
	public List<Incidencia>buscarIncidenciasPorIdHabitacion(int idHabitacion) {

			Session miSession=mySessionFactory.getCurrentSession();
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);

			habitacion.getIncidencias().size();
			
			return habitacion.getIncidencias();
		
	}

	/**
	 * Metodo que busca una incidencia en bbdd por el id.
	 * 
	 * @param idIncidencia identificador de la incidencia que se quiere consultar
	 * @return objeto Incidencia encontrado en la bbdd
	 */
	// método para obtener las incidencias por su Id
	@Override
	public Incidencia buscarIncidenciaPorId(int idIncidencia) {

			Session miSession=mySessionFactory.getCurrentSession();
			
			Incidencia incidencia=miSession.get(Incidencia.class,idIncidencia);

			if(incidencia!=null) {
				
			incidencia.getHabitacion().getNumeroHabitacion();
			
			}
			
			return incidencia;
		
	}
	
}
