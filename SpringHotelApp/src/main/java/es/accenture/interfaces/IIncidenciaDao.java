package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Incidencia;

/**
 * Interfaz DAO encargada de los métodos del crud
 * 
 * Esta interfaz declara los metodos para el crud
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IIncidenciaDao {
	
	/**
	 * Metodo encargado de obtener el listado de incidencias de bbdd
	 * 
	 * @return lista de objetos Incidencia
	 */
	List<Incidencia>buscarIncidencias(); //obtiene todos los datos de incidencias y los guarda en una lista, luego se mostrarán mediante la vista

	/**
	 * Metodo encargado de guardar una incidencia en bbdd
	 * 
	 * @param incidencia objeto Incidencia con la informacion a almacenar
	 */
	void guardarIncidencia(Incidencia incidencia); //guarda datos de una incidencia nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	/**
	 * Metodo encargado de actualizar una incidencia en bbdd
	 * 
	 * @param incidencia objeto Incidencia con la informacion modificada
	 */
	void actualizarIncidencia(Incidencia incidencia); //coge los datos de una incidencia consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	/**
	 * Metodo encargado de eliminar una incidencia en bbdd por su id
	 * 
	 * @param idIncidencia identificador de la incidencia que se desea eliminar
	 */
	void eliminarIncidencia(int idIncidencia); //borra una incidencia de bbdd y luego redirige a la jsp que muestra detalle incidencia y salen todas

	/**
	 * Metodo encargado de obtener las incidencias de una habitacion
	 * 
	 * @param idHabitacion identificador de la habitacion cuyas incidencias se quieren consultar
	 * @return lista de incidencias de la habitacion
	 */
	List<Incidencia>buscarIncidenciasPorIdHabitacion(int idHabitacion); //obtiene las incidencias asociadas a una habitación por su Id y las guarda en una List
	
	/**
	 * Metodo encargado de obtener una incidencia de bbdd por su id
	 * 
	 * @param idIncidencia identificador de la incidencia que se quiere consultar
	 * @return objeto Incidencia encontrado en bbdd
	 */
	Incidencia buscarIncidenciaPorId(int idIncidencia); //me faltaba este porque si hay que buscar una incidencia no se podía sin él

}
