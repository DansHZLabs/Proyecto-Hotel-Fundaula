package es.accenture.interfaces;                    //esto lo hace cualquiera entero A o B

import java.util.List;

import es.accenture.entity.Reserva;

public interface IReservaDao {
	
	List<Reserva>buscarReservas(); //obtiene todos los datos de reservas y los guarda en una lista, luego se mostrarán mediante la vista

	void guardarReserva (Reserva reserva); //guarda datos de una reserva nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	void actualizarReserva (Reserva reserva); //coge los datos de una reserva consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	void eliminarReserva (int idReserva); //borra una reserva de bbdd y luego redirige a la jsp que muestra detalle reserva y salen todas

	Reserva buscarReservaPorId (int id); //este voy a necesitar para ActualizarReserva y EliminarReserva, ojo! devuelve una reserva que es la que se utilizará para modificar o eliminar

}
