package es.accenture.interfaces;                      //esto lo hace cualquiera entero A o B

import java.util.List;

import es.accenture.entity.Reserva;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;

/**
 * Interfaz de servicie para los métodos del crud de reservas
 * 
 * Esta interfaz pone los métodos del crud para reservas y sus validaciones y lógica de negocio.
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IReservasService {

	/**
	 * Metodo encargado de obtener el listado de reservas de bbdd
	 * 
	 * @return lista de objetos Reserva
	 */
	List<Reserva>buscarReservas(); //obtiene todos los datos de reservas y los guarda en una lista, luego se mostrarán mediante la vista

	/**
	 * Metodo encargado de guardar una reserva en bbdd
	 * 
	 * @param reserva objeto Reserva con la informacion a guardar
	 * @throws GuardarException excepcion lanzada si ocurre un error al guardar
	 */
	void guardarReserva (Reserva reserva)throws GuardarException; //guarda datos de una reserva nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	/**
	 * Metodo encargado de actualizar la informacion de una reserva en bbdd
	 * 
	 * @param reserva objeto Reserva con la informacion modificada
	 * @throws ActualizarException excepcion lanzada si ocurre un error al actualizar
	 */
	void actualizarReserva (Reserva reserva)throws ActualizarException; //coge los datos de una reserva consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	/**
	 * Metodo encargado de eliminar una reserva en bbdd por su id
	 * 
	 * @param idReserva identificador de la reserva que se quiere eliminar
	 * @throws EliminarException excepcion lanzada si ocurre un error al eliminar
	 * @throws BuscarException excepcion lanzada si no se encuentra la reserva
	 */
	void eliminarReserva (int idReserva)throws EliminarException,BuscarException; //borra una reserva de bbdd y luego redirige a la jsp que muestra detalle reserva y salen todas

	/**
	 * Metodo encargado de obtener una reserva de bbdd por su id
	 * 
	 * @param idReserva identificador de la reserva que se quiere buscar
	 * @return objeto Reserva encontrado
	 * @throws BuscarException excepcion lanzada si no se encuentra la reserva
	 */
	Reserva buscarReservaPorId (int idReserva)throws BuscarException; //este voy a necesitar para ActualizarReserva y EliminarReserva, ojo! devuelve una reserva que es la que se utilizará para modificar o eliminar

}
