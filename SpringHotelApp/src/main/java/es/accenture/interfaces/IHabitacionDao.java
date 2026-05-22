package es.accenture.interfaces;                           //esto lo hace el A //esto lo tengo que revisar

import java.util.List;

import es.accenture.entity.Habitacion;

/**
 * Interfaz DAO encargada de definir las operaciones crud
 * 
 * Esta interfaz declara los metodos necesarios para realizar operaciones crud
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IHabitacionDao {
	
	/**
	 * Metodo encargado de obtener el listado completo de habitaciones de bbdd
	 * 
	 * @return lista de objetos Habitacion
	 */
	List<Habitacion>buscarHabitaciones(); //obtiene todos los datos de habitaciones y los guarda en una lista, luego se mostrarán mediante la vista

	/**
	 * Metodo encargado de guardar una nueva habitacion en bbdd
	 * 
	 * @param habitacion objeto Habitacion con la informacion a almacenar
	 */
	void guardarHabitacion (Habitacion habitacion); //guarda datos de una habitacion nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	/**
	 * Metodo encargado de actualizar la informacion de una habitacion en bbdd
	 * 
	 * @param habitacion objeto Habitacion con la informacion modificada
	 */
	void actualizarHabitacion (Habitacion habitacion); //coge los datos de una habitación consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	/**
	 * Metodo encargado de eliminar una habitacion de bbdd por su id
	 * 
	 * @param idHabitacion identificador de la habitacion que se quiere eliminar
	 */
	void eliminarHabitacion (int idHabitacion); //borra una habitación de bbdd y luego redirige a la jsp que muestra detalle habitacion y salen todas

	/**
	 * Metodo encargado de obtener una habitacion de bbdd por su id
	 * 
	 * @param id identificador de la habitacion que se quiere consultar
	 * @return objeto Habitacion encontrado en la base de datos
	 */
	Habitacion buscarHabitacionPorId (int id); //este voy a necesitar para ModificarHabitacion y EliminarHabitacion, ojo! devuelve una habitación que es la que se utilizará para modificar o eliminar

}
