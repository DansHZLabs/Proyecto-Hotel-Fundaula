package es.accenture.interfaces;

import java.util.List;

import es.accenture.entity.Usuario;

/**
 * Interfaz que genera un desacoplamiento al poder utilizar cualquier tipo de
 * DAO que la implemente, abstrayendo asi los distintos Service del DAO usado
 * 
 * @author danih y javi
 * @version 1.0
 *
 */
public interface IUsuarioDao {

	// Metodo a implementar por el UsuarioDAO

	/**
	 * Metodo que se comunica con la BBDD para comprobar si existe el usuario con el
	 * nombre obtenido del formulario de Login jsp, devolviendo el resto de
	 * informacion de ese registro
	 * 
	 * @param usuario
	 * @return
	 */
	List<Usuario> buscarUsuario(String usuario);

}
