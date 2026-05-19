package es.accenture.interfaces;                    //esto lo hace el A

import java.util.List;

import es.accenture.entity.Incidencia;
import es.accenture.exceptions.ActualizarException;
import es.accenture.exceptions.BuscarException;
import es.accenture.exceptions.EliminarException;
import es.accenture.exceptions.GuardarException;

public interface IIncidenciaService {
	
	List<Incidencia>buscarTodasIncidencias(); //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la obtención de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	void guardarIncidencia (Incidencia incidencia)throws GuardarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo del alta de habitaciones, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd
	
	void actualizarIncidencia (Incidencia incidencia)throws ActualizarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la modificación de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	void eliminarIncidencia (int id)throws EliminarException,BuscarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la eliminación de incidencias, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	List<Incidencia>buscarIncidenciasPorIdHabitacion (int idHabitacion)throws BuscarException; //método donde se meterá la lógica de negocio en IncidenciaService al sobreescribirlo de la obtención de incidencias de una habitación por el Id de la habitación, ojo! devuelve una lista que es donde se guardarán las incidencias de esa habitación, ojo se llaman igual que el método de IncidenciaDao pero no hacen lo mismo este es para la lógica y el otro para la bbdd

	Incidencia buscarIncidenciaPorId(int idIncidencia)throws BuscarException; //me faltaba este porque si hay que buscar una incidencia no se podía sin él
	
}
