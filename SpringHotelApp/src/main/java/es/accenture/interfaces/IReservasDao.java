package es.accenture.interfaces;                    //esto lo hace cualquiera entero A o B

import java.util.List;

import es.accenture.entity.Reserva;

/**
 * Interfaz DAO encargada del crud de acceso a bbdd de reservas.
 * 
 * Esta interfaz establece los métodos del crud.
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IReservasDao {
	
	/**
	 * Metodo encargado de obtener el listado de reservas de bbdd
	 * 
	 * @return lista de objetos Reserva
	 */
	List<Reserva>buscarReservas(); //obtiene todos los datos de reservas y los guarda en una lista, luego se mostrarán mediante la vista

	/**
	 * Metodo encargado de guardar una reserva en bbdd
	 * 
	 * @param reserva objeto Reserva con la informacion a almacenar
	 */
	void guardarReserva (Reserva reserva); //guarda datos de una reserva nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	/**
	 * Metodo encargado de actualizar la informacion de una reserva en bbdd
	 * 
	 * @param reserva objeto Reserva con la informacion modificada
	 */
	void actualizarReserva (Reserva reserva); //coge los datos de una reserva consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	/**
	 * Metodo encargado de eliminar una reserva de bbdd
	 * 
	 * @param idReserva identificador de la reserva que se quiere eliminar
	 */
	void eliminarReserva (int idReserva); //borra una reserva de bbdd y luego redirige a la jsp que muestra detalle reserva y salen todas

	/**
	 * Metodo encargado de obtener una reserva de bbdd por su id
	 * 
	 * @param id identificador de la reserva que se quiere consultar
	 * @return objeto Reserva encontrado en bbdd
	 */
	Reserva buscarReservaPorId (int id); //este voy a necesitar para ActualizarReserva y EliminarReserva, ojo! devuelve una reserva que es la que se utilizará para modificar o eliminar

}
