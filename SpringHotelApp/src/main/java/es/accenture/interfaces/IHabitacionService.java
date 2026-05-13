package es.accenture.interfaces;                   //esto lo hace el A

import java.util.List;

import es.accenture.entity.Habitacion;

public interface IHabitacionService {

	List<Habitacion>buscarHabitaciones(); //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la obtención de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	void guardarHabitacion(Habitacion habitacion); //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo del alta de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
	void actualizarHabitacion(Habitacion habitacion); //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la modificación de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	void eliminarHabitacion(int id)throws Exception; //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la eliminación de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	Habitacion buscarHabitacionPorId(int id); //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la obtención de habitaciones por Id, ojo! devuelve una habitación que es la que se utilizará para modificar o eliminar, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
}
