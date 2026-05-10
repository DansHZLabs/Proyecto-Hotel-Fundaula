package es.accenture.exceptions;

/**
 * 
 * @author danih y javi
 * @version 1.0
 */
public class ExcepcionUsuario extends Exception {
	
	// Constantes para los mensajes de error en funcion de su tipo
	
	public static final String CREDENCIALES_VACIAS = "Error, por favor rellene ambas credenciales para verificar el acceso.";
	
	public static final String USUARIO_INCORRECTO = "Error, usuario incorrecto, por favor inserte un usuario existente";
	
	public static final String PASSWORD_INCORRECTA = "Error, usuario incorrecto, por favor inserte un password correcto";
	
	
	/*
	 * Constructor (lo llamaremos en otras clases para establecer el texto
	 * personalizado de error en cada caso)
	 */
	public ExcepcionUsuario(String mensaje) {
		super(mensaje);
	}
	

}
