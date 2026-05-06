package es.accenture.service;                  //esto lo hace el A

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.entity.Habitacion;
import es.accenture.interfaces.IHabitacionDao;
import es.accenture.interfaces.IHabitacionService;

@Service //Anotación para decirle a Spring que es un service
public class HabitacionService implements IHabitacionService{

	@Autowired //Anotación para hacer la inyección de dependencias de HabitacionDao aquí sin hacer new HabitacionDao=habitacionDao
	private IHabitacionDao habitacionDao;
	
	//a partir de aquí hay que ir llamando a los métodos del contrato con IHabitacionService y meter la lógica y luego ir llamando a los que HabitacionDao ha ido sobreescribiendo de IHabitacionDao y así sacar datos
	//método para obtener todas las habitaciones
	@Override //Anotación para sobreescribir el método de la interfaz
	public List<Habitacion> obtenerTodasHabitaciones() {
		// TODO Auto-generated method stub
		return habitacionDao.obtenerDetallesTodasHabitaciones(); //aquí se llama a dao que consulta bbdd y devuelve la lista
	}

	//método para obtener una habitación por Id
    @Override //Anotación para sobreescribir el método de la interfaz
    public Habitacion obtenerHabitacionPorId(int id) {

    	//poner aquí la lógica
        return habitacionDao.obtenerHabitacionPorId(id); //aquí se llama a dao que consulta una habitación por el parámetro id
    }
    
    
    //método para el alta de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void altaHabitacion(Habitacion habitacion) {
        // Aquí habría que poner las validaciones y lógica de negocio, los if y todo eso
        habitacionDao.altaHabitacion(habitacion); //aquí se llama a dao para guardar la habitación
    }
    
    
    //método para la modificación de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void modificarHabitacion(Habitacion habitacion) {

        // aquí hay que poner la lógica
        habitacionDao.modificarHabitacion(habitacion); //aquí se llama a dao para actualizar en la bbdd
    }
    
    
    //método para la eliminación de una habitación
    @Override //Anotación para sobreescribir el método de la interfaz
    public void eliminarHabitacion(int id) {

        // Aquí va la lógica if tiene incidencias no se puede borrar y cosas así, está apuntado todo lo que acordamos en la reunión en un bloc de notas, buscar en la carpeta y pegar

        habitacionDao.eliminarHabitacion(id); //aquí se llama a dao para borrar de la bbdd
    }
	
}
