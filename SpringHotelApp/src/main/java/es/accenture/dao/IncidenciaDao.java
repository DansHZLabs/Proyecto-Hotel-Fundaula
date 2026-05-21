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

@Transactional //para que Spring gestione automáticamente las transacciones
@Repository // Anotación para decirle a Spring que esta clase es un DAO para acceder a bbdd
public class IncidenciaDao implements IIncidenciaDao {

    private SessionFactory mySessionFactory;

    @Autowired
    public IncidenciaDao(SessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }
    // método para obtener detalles de todas las incidencias
	@Override
	public List<Incidencia>buscarIncidencias() {
		
		List<Incidencia>incidencias=null;
		
			Session miSession=mySessionFactory.getCurrentSession();

			incidencias = miSession.createQuery("from Incidencia",Incidencia.class).getResultList();

		return incidencias;

	}
	
	// método para hacer el alta de una incidencia
	@Override
	public void guardarIncidencia(Incidencia incidencia) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.save(incidencia);
		
	}

	// método para hacer modificación de una incidencia
	@Override
	public void actualizarIncidencia(Incidencia incidencia) {

			Session miSession=mySessionFactory.getCurrentSession();

			miSession.update(incidencia);
			
	}	
	
	// método para eliminar una incidencia
	@Override
	public void eliminarIncidencia(int idIncidencia) { //void que no devuelve nada, solo borra

			Session miSession=mySessionFactory.getCurrentSession();

			Incidencia incidencia=miSession.get(Incidencia.class,idIncidencia);

			if(incidencia!=null) {

				miSession.delete(incidencia);
			}
	
	}		

	 // método para obtener las incidencias de una habitación por su Id
	@Override
	public List<Incidencia>buscarIncidenciasPorIdHabitacion(int idHabitacion) {

			Session miSession=mySessionFactory.getCurrentSession();
			
			Habitacion habitacion=miSession.get(Habitacion.class,idHabitacion);

			habitacion.getIncidencias().size();
			
			return habitacion.getIncidencias();
		
	}

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
