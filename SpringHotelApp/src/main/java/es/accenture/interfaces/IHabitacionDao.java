package es.accenture.interfaces;                           //esto lo hace el A //esto lo tengo que revisar

import java.util.List;

import es.accenture.entity.Habitacion;

public interface IHabitacionDao {
	
	List<Habitacion>obtenerDetallesTodasHabitaciones(); //obtiene todos los datos de habitaciones y los guarda en una lista, luego se mostrarán mediante la vista

	void altaHabitacion (Habitacion habitacion); //guarda datos de una habitacion nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	void modificarHabitacion (Habitacion habitacion); //coge los datos de una habitación consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	void eliminarHabitacion (int id); //borra una habitación de bbdd y luego redirige a la jsp que muestra detalle habitacion y salen todas

	Habitacion obtenerHabitacionPorId (int id); //este voy a necesitar para ModificarHabitacion y EliminarHabitacion, ojo! devuelve una habitación que es la que se utilizará para modificar o eliminar

}
