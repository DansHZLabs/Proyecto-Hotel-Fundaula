package es.accenture.exceptions;

/**
 * Clase que genera excepciones personalizadas relacionadas con la accion de
 * 'Login'
 * 
 * @author danih y javi
 * @version 1.0
 */
public class ExcepcionUsuario extends Exception {

	// Constantes para los mensajes de error en funcion de su tipo

	public static final String CREDENCIALES_VACIAS = "Error, por favor rellene ambas credenciales para verificar el acceso.";

	public static final String USUARIO_INCORRECTO = "Error, usuario incorrecto, por favor inserte un usuario existente";

	public static final String PASSWORD_INCORRECTA = "Error, password incorrecto, por favor inserte un password correcto";

	/**
	 * Constructor de excepciones (lo llamaremos en otras clases para establecer
	 * el texto personalizado de error en cada caso). Utilizo el super porque el
	 * error que voy a usar en el catch en la logica de negocio va a ser de tipo
	 * Exception (ya que asi coge tmbn los errores no personalizados), por lo que
	 * para que el mensaje se almacene en la clase Exeption hay que usar una
	 * referencia a su constructor.
	 * 
	 * @param mensaje de excepcion personalizado
	 */
	public ExcepcionUsuario(String mensaje) {
		super(mensaje);
	}

}
