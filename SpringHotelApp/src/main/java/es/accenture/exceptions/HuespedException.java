package es.accenture.exceptions;

/**
 * Clase que genera excepciones personalizadas relacionadas con la accion de
 * 'huespedes'
 * 
 * @author danih y javi
 * @version 1.0
 */
public class HuespedException extends Exception {
	
	// Constantes para los mensajes de error en funcion de su tipo
	
	public static final String GuardarException = "Error, si desea crear un nuevo huesped es obligatorio que rellene todos los campos. El email es opcional.";
	
	public static final String ActualizarException = "Error, si desea actualizar los datos de este huesped es obligatorio que rellene todos los campos. El email es opcional.";
	
	public static final String TelefonoDuplicadoException = "Error, no se pudieron guardar los datos debido a que ya existe un huesped con el mismo numero de telefono.";
	
	public static final String EliminarException = "Error al eliminar, existen reservas pendientes asociadas a este huesped. Por favor cierre primero dichas reservas antes de borrar el huesped.";
	
	public static final String BuscarException = "Lista vacía, por favor inserte un nuevo Huesped si desea obtener resultados.";
	

	
	
	/**
	 * Constructor de excepciones (lo llamaremos en otras clases para establecer
	 * el texto personalizado de error en cada caso). Utilizo el super porque el
	 * error que voy a usar en el catch en la logica de negocio va a ser de tipo
	 * Exception (ya que asi coge tmbn los errores no personalizados), por lo que
	 * para que el mensaje se almacene en la clase Exception hay que usar una
	 * referencia a su constructor.
	 * 
	 * @param mensaje de excepcion personalizado
	 */
	public HuespedException(String mensaje) {
		super(mensaje);
	}
	
	
	
	

}
