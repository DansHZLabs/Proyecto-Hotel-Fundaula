package es.accenture.Interfaces;

import java.util.List;

import es.accenture.entity.Usuario;

/**
 * Interfaz que genera un desacoplamiento al poder utilizar cualquier tipo de
 * DAO que implemente esta interfaz, abstrayendo los distintos Service del DAO
 * usado
 * 
 * @author danih y javi
 * @version 1.0
 *
 */
public interface InUsuarioDAO {

	// Metodo a implementar por el UsuarioDAO

	List<Usuario> obtenerUsuario(String usuario, String password);

}
