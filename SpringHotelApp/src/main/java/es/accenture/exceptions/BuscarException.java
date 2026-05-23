package es.accenture.exceptions;

/**
 * Clase de excepcion personalizada utilizada para errores
 * producidos al buscar.
 * 
 * Esta excepcion permite mostrar mensajes de error al buscar.
 * 
 * @author danih y javi
 * @version 1.0
 */
public class BuscarException extends Exception{

	/**
	 * Constructor por parametros que permite crear la excepcion
	 * y saca un mensaje de error personalizado.
	 * 
	 * @param mensaje descripcion del error
	 */
	public BuscarException(String mensaje) {

		super(mensaje);

	}

}