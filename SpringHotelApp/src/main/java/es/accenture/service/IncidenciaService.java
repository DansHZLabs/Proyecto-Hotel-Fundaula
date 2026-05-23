package es.accenture.service;                   //esto lo hace el A

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Incidencia;
import es.accenture.entity.Incidencia.EstadoIncidencia;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;
import es.accenture.interfaces.IIncidenciaDao;
import es.accenture.interfaces.IIncidenciaService;

/**
 * Clase de servicio encargada de la logica de negocio de las incidencias.
 * 
 * Esta clase se hace el crud y la lógica de negocio antes de ir a bbdd
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service //Anotación para decirle a Spring que es un service
public class IncidenciaService implements IIncidenciaService {

	/*
	 * Atributo donde se almacena el DAO de incidencias encargado del acceso a bbdd
	 */
	private IIncidenciaDao incidenciaDao;

	/**
	 * Constructor por parametros en el que se realiza la inyeccion
	 * de dependencias del DAO de incidencias.
	 * 
	 * @param incidenciaDao DAO encargado del acceso a datos de incidencias
	 */
	@Autowired //inyección por constructor
	public IncidenciaService(IIncidenciaDao incidenciaDao) {
	    this.incidenciaDao = incidenciaDao;
	}
	
	/**
	 * Metodo encargado de sacar el listado de incidencias de bbdd
	 * 
	 * @return lista de objetos Incidencia
	 */
	//método para obtener todas las incidencias
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Incidencia>buscarTodasIncidencias() {
		// TODO Auto-generated method stub

		 return incidenciaDao.buscarIncidencias(); //aquí se llama a dao que consulta todas las incidencias en bbdd y devuelve la lista
    
    }

	/**
	 * Metodo encargado de guardar una incidencia en bbdd
	 * 
	 * @param incidencia objeto Incidencia con la informacion a guardar
	 * @throws GuardarException excepcion lanzada si los datos no son validos
	 */
	//método para el alta de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void guardarIncidencia(Incidencia incidencia)throws GuardarException {
		// TODO Auto-generated method stub
    	
    	if(incidencia==null||incidencia.getFechaApertura()==null){throw new GuardarException("Error al guardar la incidencia");}//hay que comprobar la incidencia y la fecha
    	
    	incidenciaDao.guardarIncidencia(incidencia); //aquí se llama a dao para guardar la incidencia
	}

    /**
     * Metodo encargado de actualizar una incidencia en bbdd
     * 
     * @param incidencia objeto Incidencia con la informacion modificada
     * @throws ActualizarException excepcion lanzada si los datos no son validos
     */
    //método para la modificación de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void actualizarIncidencia(Incidencia incidencia)throws ActualizarException {
		// TODO Auto-generated method stub
    	
    	if(incidencia==null||incidencia.getFechaApertura()==null){throw new ActualizarException("Error al actualizar la incidencia");}//si no hay incidencia lanzar excepción
		
    	incidenciaDao.actualizarIncidencia(incidencia); //aquí se llama a dao para actualizar en la bbdd
	}

    /**
     * Metodo encargado de eliminar una incidencia de bbdd
     * 
     * @param idIncidencia identificador de la incidencia que se quiere eliminar
     * @throws EliminarException excepcion lanzada si la incidencia no puede eliminarse
     * @throws BuscarException excepcion lanzada si la incidencia no existe
     */
    //método para la eliminación de una incidencia
    @Override //Anotación para sobreescribir el método de la interfaz
	public void eliminarIncidencia(int idIncidencia) throws EliminarException,BuscarException {
		// TODO Auto-generated method stub
    	
    	// obtener la incidencia
    	Incidencia incidencia=incidenciaDao.buscarIncidenciaPorId(idIncidencia); //se obtiene por el Id a través del dao
    	
    	//si no hay incidencia porque es igual a null
    	if(incidencia==null) {throw new BuscarException("La incidencia no existe");}
    	
    	//poner la lógica aquí, no eliminar si la incidencia no tiene de estado cerrada
    	if(incidencia.getEstadoIncidencia()!=EstadoIncidencia.CERRADA) {throw new EliminarException("La incidencia no se puede eliminar porque no está cerrada");}

    	incidenciaDao.eliminarIncidencia(idIncidencia); //lo manda al dao para borrarlo en bbdd
		
	}

    /**
     * Metodo encargado de obtener una incidencia en bbdd por su id
     * 
     * @param idIncidencia identificador de la incidencia que se quiere consultar
     * @return objeto Incidencia encontrado
     * @throws BuscarException excepcion lanzada si la incidencia no existe
     */
    // método para obtener una incidencia por su id
    @Override
    public Incidencia buscarIncidenciaPorId(int idIncidencia)throws BuscarException {

    	Incidencia incidencia=incidenciaDao.buscarIncidenciaPorId(idIncidencia);

    	if(incidencia==null){throw new BuscarException("La incidencia no existe");
    	
    	}

    	return incidencia;
    }

	/**
	 * Metodo encargado de obtener las incidencias de una habitacion.
	 * 
	 * @param idHabitacion identificador de la habitacion cuyas incidencias se quieren consultar
	 * @return lista de incidencias de la habitacion
	 */
	@Override
	public List<Incidencia>buscarIncidenciasPorIdHabitacion(int idHabitacion) {
		// TODO Auto-generated method stub
		
		return incidenciaDao.buscarIncidenciasPorIdHabitacion(idHabitacion); // llama al dao para buscar en bbdd las incidencias por el id de habitación y las devuelve en la lista
		
	}
	
}
