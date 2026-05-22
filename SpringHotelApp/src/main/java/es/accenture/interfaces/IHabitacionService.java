package es.accenture.interfaces;                   //esto lo hace el A

import java.util.List;

import es.accenture.entity.Habitacion;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;

/**
 * Interfaz de service encargada de establecer los métodos del crud
 * 
 * Esta interfaz declara los metodos para el crud y sus validaciones y lógica de negocio
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IHabitacionService {

	/**
	 * Metodo encargado de obtener el listado  de habitaciones
	 * 
	 * @return lista de objetos Habitacion
	 */
	List<Habitacion>buscarHabitaciones(); //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la obtención de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de guardar una habitacion.
	 * 
	 * @param habitacion objeto Habitacion con la informacion a guardar
	 * @throws GuardarException excepcion lanzada si ocurre un error al guardar
	 */
	void guardarHabitacion(Habitacion habitacion)throws GuardarException; //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo del alta de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
	/**
	 * Metodo encargado de actualizar la informacion de una habitacion.
	 * 
	 * @param habitacion objeto Habitacion con la informacion modificada
	 * @throws ActualizarException excepcion lanzada si ocurre un error al actualizar
	 */
	void actualizarHabitacion(Habitacion habitacion)throws ActualizarException; //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la modificación de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de eliminar una habitacion por su id
	 * 
	 * @param id identificador de la habitacion que se quiere eliminar
	 * @throws EliminarException excepcion lanzada si ocurre un error al eliminar
	 * @throws BuscarException excepcion lanzada si no se encuentra la habitacion
	 */
	void eliminarHabitacion(int id)throws EliminarException, BuscarException; //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la eliminación de habitaciones, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	/**
	 * Metodo encargado de obtener una habitacion por su id
	 * 
	 * @param id identificador de la habitacion que se quiere consultar
	 * @return objeto Habitacion encontrado
	 * @throws BuscarException excepcion lanzada si no se encuentra la habitacion
	 */
	Habitacion buscarHabitacionPorId(int id) throws BuscarException; //método donde se meterá la lógica de negocio en HabitacionService al sobreescribirlo de la obtención de habitaciones por Id, ojo! devuelve una habitación que es la que se utilizará para modificar o eliminar, ojo se llaman igual que el método de HabitacionDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
}
