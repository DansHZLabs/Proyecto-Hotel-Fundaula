package es.accenture.interfaces;                   //esto lo hace el A

import java.util.List;

import es.accenture.entity.Habitacion;

public interface IHabitacionService {

	List<Habitacion>obtenerTodasHabitaciones(); //lista donde se guardan los datos de las habitaciones
	
}
