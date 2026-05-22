package es.accenture.interfaces;                    //esto lo hace el A

import java.util.List;

import es.accenture.entity.Incidencia;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;

/**
 * Interfaz de servicie para la lógica de negocio y el crud
 * 
 * Esta interfaz declara los metodos el crud de incidencias y la lógica.
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IIncidenciaService {
	
	/**
	 * Metodo encargado de obtener el listado de incidencias de bbdd
	 * 
	 * @return lista de objetos Incidencia
	 */
	List<Incidencia>buscarTodasIncidencias(); //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la obtención de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de guardar una incidencia en bbdd
	 * 
	 * @param incidencia objeto Incidencia con la informacion a guardar
	 * @throws GuardarException excepcion lanzada si ocurre un error al guardar
	 */
	void guardarIncidencia (Incidencia incidencia)throws GuardarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo del alta de habitaciones, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
	/**
	 * Metodo encargado de actualizar la informacion de una incidencia en bbdd
	 * 
	 * @param incidencia objeto Incidencia con la informacion modificada
	 * @throws ActualizarException excepcion lanzada si ocurre un error al actualizar
	 */
	void actualizarIncidencia (Incidencia incidencia)throws ActualizarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la modificación de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de eliminar una incidencia en bbdd por su id
	 * 
	 * @param id identificador de la incidencia que se quiere eliminar
	 * @throws EliminarException excepcion lanzada si ocurre un error al eliminar
	 * @throws BuscarException excepcion lanzada si no se encuentra la incidencia
	 */
	void eliminarIncidencia (int id)throws EliminarException,BuscarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la eliminación de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de obtener las incidencias de una habitacion.
	 * 
	 * @param idHabitacion identificador de la habitacion cuyas incidencias se quieren consultar
	 * @return lista de incidencias de la habitacion
	 * @throws BuscarException excepcion lanzada si no se encuentra la habitacion
	 */
	List<Incidencia>buscarIncidenciasPorIdHabitacion (int idHabitacion)throws BuscarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la obtención de incidencias de una habitación por el Id de la habitación, ojo! devuelve una lista que es donde se guardarán las incidencias de esa habitación, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de obtener una incidencia en bbdd por su id
	 * 
	 * @param idIncidencia identificador de la incidencia que se quiere consultar
	 * @return objeto Incidencia encontrado
	 * @throws BuscarException excepcion lanzada si no se encuentra la incidencia
	 */
	Incidencia buscarIncidenciaPorId(int idIncidencia)throws BuscarException; //me faltaba este porque si hay que buscar una incidencia no se podía sin él
	
}
