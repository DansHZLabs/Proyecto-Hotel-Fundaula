package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Huesped;
import es.accenture.exceptions.HuespedException;

/**
 * Interfaz que genera un desacoplamiento al poder utilizar cualquier tipo de
 * DAO que la implemente, abstrayendo asi los distintos Service del DAO usado
 * 
 * @author danih y javi
 * @version 1.0
 *
 */
public interface IHuespedDao {

	// Metodos a implementar por el HuespedDao

	/**
	 * Metodo que obtiene todos los huespedes y algunas de sus caracteristicas (ID,
	 * nombre y apellidos) de la BBDD
	 * 
	 * @return Lista con objetos de tipo Huesped (entidad ORM)
	 */
	List<Huesped> listarHuespedes();

	/**
	 * Metodo que obtiene todas las caracteristicas de un determinado Huesped de la
	 * BBDD
	 * 
	 * @param idHuesped (identificador del huesped a obtener)
	 * @return objeto de tipo Huesped (entidad ORM)
	 */
	Huesped buscarHuesped(int idHuesped);

	/**
	 * Metodo que actualiza los datos de un determinado Huesped de la BBDD
	 * 
	 * @param Huesped (objeto de tipo huesped ORM con toda la info a actualizar)
	 * @throws HuespedException 
	 */
	void actualizarHuesped(Huesped huespedModificado) throws HuespedException;

	/**
	 * Metodo que elimina un determinado Huesped y sus caracteristicas de la BBDD
	 * 
	 * @param idHuesped (identificador del huesped a eliminar)
	 */
	void eliminarHuesped(int idHuesped);

	/**
	 * Metodo que guarda un nuevo Huesped en la BBDD con los datos que le asignemos
	 * 
	 * @param huespedNuevo (objeto de tipo huesped ORM con toda la info a crear)
	 * @throws HuespedException 
	 */
	void guardarHuesped(Huesped huespedNuevo) throws HuespedException;

	/**
	 * Metodo que comprueba si el telefono guardado en el formulario rellenado por el usuario, existe en la BBDD.
	 * En caso afirmativo se envia un mensaje de error para que el usuario lo cambie por otro no coincidente(
	 * se supone que tiene que haber un telefono unico por usuario)
	 * @param emailFormulario
	 * @throws HuespedException 
	 */
	void comprobarDuplicadoTelefonoHuesped(String telefonoFormularioHuesped) throws HuespedException;

}
