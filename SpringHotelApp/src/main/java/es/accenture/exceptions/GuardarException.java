package es.accenture.exceptions;

/**
 * Clase de excepcion personalizada utilizada para errores
 * producidos al guardar.
 * 
 * Esta excepcion permite mostrar mensajes de error al guardar.
 * 
 * @author danih y javi
 * @version 1.0
 */
public class GuardarException extends Exception{

	/**
	 * Constructor por parametros que permite crear la excepcion
	 * y saca un mensaje de error personalizado.
	 * 
	 * @param mensaje descripcion del error
	 */
	public GuardarException(String mensaje) {

		super(mensaje);

	}

}