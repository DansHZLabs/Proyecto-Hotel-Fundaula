package es.accenture.interfaces;

/**
 * Interfaz que genera un desacoplamiento al poder utilizar cualquier tipo de
 * Service que la implemente, abstrayendo asi los distintos Controller del
 * Service usado
 * 
 * @author danih y javi
 * @version 1.0
 */
public interface IUsuarioService {

	// Metodo a implementar por el UsuarioService

	/**
	 * Metodo que comprueba las credenciales del usuario para dar o denegar el
	 * acceso a la pagina principal de funciones del hotel
	 * 
	 * @param usuario
	 * @param password
	 * @throws Exception
	 */
	void loginUsuario(String usuario, String password) throws Exception;
}
