package es.accenture.service;                   //esto lo hace el A

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Incidencia;
import es.accenture.entity.Incidencia.EstadoIncidencia;
import es.accenture.interfaces.IIncidenciaDao;
import es.accenture.interfaces.IIncidenciaService;

@Service //Anotación para decirle a Spring que es un service
public class IncidenciaService implements IIncidenciaService {

	@Autowired //Anotación para hacer la inyección de dependencias de HabitacionDao aquí sin hacer new HabitacionDao=habitacionDao
	private IIncidenciaDao incidenciaDao;
	
	//a partir de aquí hay que ir llamando a los métodos del contrato con IIncidenciaService y meter la lógica y luego ir llamando a los que IncidenciaDao ha ido sobreescribiendo de IIncidenciaDao y así sacar datos
	//método para obtener todas las incidencias
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Incidencia>buscarTodasIncidencias() {
		// TODO Auto-generated method stub

		 return incidenciaDao.buscarIncidencias(); //aquí se llama a dao que consulta todas las incidencias en bbdd y devuelve la lista
    
    }

	//método para el alta de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void guardarIncidencia(Incidencia incidencia) {
		// TODO Auto-generated method stub
    	incidenciaDao.guardarIncidencia(incidencia); //aquí se llama a dao para guardar la incidencia
	}

    //método para la modificación de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void actualizarIncidencia(Incidencia incidencia) {
		// TODO Auto-generated method stub
		
    	incidenciaDao.actualizarIncidencia(incidencia); //aquí se llama a dao para actualizar en la bbdd
	}

    //método para la eliminación de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void eliminarIncidencia(int idIncidencia) throws Exception {
		// TODO Auto-generated method stub
    	
    	// obtener la incidencia
    	Incidencia incidencia=incidenciaDao.buscarIncidenciaPorId(idIncidencia); //se obtiene por el Id a través del dao
    	
    	//si no hay incidencia porque es igual a null
    	if(incidencia==null) {throw new Exception("La incidencia no existe");}
    	
    	//poner la lógica aquí, no eliminar si la incidencia no tiene de estado cerrada
    	if(incidencia.getEstadoIncidencia()!=EstadoIncidencia.cerrada) {throw new Exception("La incidencia no se puede eliminar porque no está cerrada");}

    	incidenciaDao.eliminarIncidencia(idIncidencia); //lo manda al dao para borrarlo en bbdd
		
	}

    // método para obtener una incidencia por su id
    @Override
    public Incidencia buscarIncidenciaPorId(int idIncidencia) {

        return incidenciaDao.buscarIncidenciaPorId(idIncidencia); //llama al dao para buscar la incidencia en bbdd y la devuelve
    }

	@Override
	public List<Incidencia>buscarIncidenciasPorIdHabitacion(int idHabitacion) {
		// TODO Auto-generated method stub
		
		return incidenciaDao.buscarIncidenciasPorIdHabitacion(idHabitacion); // llama al dao para buscar en bbdd las incidencias por el id de habitación y las devuelve en la lista
		
	}
	
}
