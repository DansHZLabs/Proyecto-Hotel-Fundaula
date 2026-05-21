package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedException;

/**
 * Interfaz que genera un desacoplamiento al poder utilizar cualquier tipo de
 * Service que la implemente, abstrayendo asi los distintos Controller del
 * Service usado
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IHuespedService {

	// Metodos a implementar por el HuespedService
	
	
	/**
	 *  Metodo que muestra todos los huespedes y sus caracteristicas almacenados en la BBDD
	 * @return Lista con objetos de tipo Huesped	 * 
	 * @throws Exception
	 */
	List<Huesped> listarHuesped() throws Exception;
	
	
	
	/**
	 * Metodo que busca un determinado Huesped en la BBDD devolviendo sus caracteristicas si existen coincidencias
	 * @param idHuesped (este identificador viaja en el objeto Model de la request)
	 * @return Objeto de tipo Huesped
	 */
	Huesped buscarHuesped (int idHuesped);
	
	
	/**
	 * Metodo que modifica los datos de un determinado huesped a partir de los datos del formulario jsp rellenado por el usuario
	 * @param huespedModificado (este identificador viaja en el objeto Model de la request)
	 * @throws Exception 
	 */
	void actualizarHuesped (Huesped huespedModificado) throws Exception;
	
	
	/**
	 * Metodo que elimina un determinado huesped de la BBDD
	 * @param idHuesped (este identificador viaja en el objeto Model de la request)
	 */
	void eliminarHuesped (int idHuesped) throws Exception;
	
	
	/**
	 * Metodo que almacena los datos de un nuevo huesped a partir de los datos del formulario jsp rellenado por el usuario
	 * @param huespedNuevo (este identificador viaja en el objeto Model de la request)
	 * @throws Exception 
	 */
	void guardarHuesped (Huesped huespedNuevo) throws Exception;
	
}
