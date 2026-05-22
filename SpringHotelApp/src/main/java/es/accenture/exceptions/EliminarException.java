package es.accenture.exceptions;

/**
 * Clase de excepcion personalizada utilizada para errores
 * producidos al eliminar.
 * 
 * Esta excepcion permite mostrar mensajes de error al eliminar.
 * 
 * @author danih y javi
 * @version 1.0
 */
public class EliminarException extends Exception{

	/**
	 * Constructor por parametros que permite crear la excepcion
	 * y saca un mensaje de error personalizado.
	 * 
	 * @param mensaje descripcion del error
	 */
	public EliminarException(String mensaje) {

		super(mensaje);

	}

}