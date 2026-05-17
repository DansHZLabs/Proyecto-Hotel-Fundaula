package es.accenture.interfaces;                      //esto lo hace cualquiera entero A o B

import java.util.List;

import es.accenture.entity.Reserva;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;

public interface IReservasService {

	List<Reserva>buscarReservas(); //obtiene todos los datos de reservas y los guarda en una lista, luego se mostrarán mediante la vista

	void guardarReserva (Reserva reserva)throws GuardarException; //guarda datos de una reserva nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	void actualizarReserva (Reserva reserva)throws ActualizarException; //coge los datos de una reserva consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	void eliminarReserva (int idReserva)throws EliminarException,BuscarException; //borra una reserva de bbdd y luego redirige a la jsp que muestra detalle reserva y salen todas

	Reserva buscarReservaPorId (int idReserva)throws BuscarException; //este voy a necesitar para ActualizarReserva y EliminarReserva, ojo! devuelve una reserva que es la que se utilizará para modificar o eliminar

}
