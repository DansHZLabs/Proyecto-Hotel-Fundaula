package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Incidencia;

public interface IIncidenciaDao {
	
	List<Incidencia>obtenerDetallesTodasIncidencias(); //obtiene todos los datos de incidencias y los guarda en una lista, luego se mostrarán mediante la vista

	void altaIncidencia (Incidencia incidencia); //guarda datos de una incidencia nueva y los añade a bbdd, los solicita antes en formulario vacío en una jsp
	
	void modificarIncidencia (Incidencia incidencia); //coge los datos de una incidencia consultando bbdd, los muestra en formulario mediante una jsp y guarda luego lo que se cambie en bbdd

	void eliminarIncidencia (int idIncidencia); //borra una incidencia de bbdd y luego redirige a la jsp que muestra detalle incidencia y salen todas

	List<Incidencia>obtenerIncidenciasPorIdHabitacion (int idHabitacion); //obtiene las incidencias asociadas a una habitación por su Id y las guarda en una List

}








/*
El acceso a datos de incidencias: una interfaz y su implementación con las 
operaciones CRUD necesariasy una consulta por habitaciónque devuelva las incidencias
asociadas a una habitación concreta.*/ //la última hay que guardarla en una lista