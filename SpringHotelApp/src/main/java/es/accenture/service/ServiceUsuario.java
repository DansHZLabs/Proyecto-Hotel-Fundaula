package es.accenture.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.accenture.Interfaces.InServiceUsuario;
import es.accenture.Interfaces.InUsuarioDAO;
import es.accenture.entity.Usuario;
import es.accenture.exceptions.ExcepcionUsuario;

/**
 * 
 * @author danih y javi
 * @version 1.0
 */
@Service
public class ServiceUsuario implements InServiceUsuario {

	@Autowired
	private InUsuarioDAO repositorioUsuario;

	@Autowired
	private HttpSession session;

	@Override
	public void loginUsuario(String usuario, String password) throws Exception {

		if (usuario.isEmpty() || password.isEmpty()) {

			throw new ExcepcionUsuario(ExcepcionUsuario.CREDENCIALES_VACIAS);

		}

		List<Usuario> listaUsers = repositorioUsuario.obtenerUsuario(usuario);

		if (listaUsers.isEmpty()) {

			throw new ExcepcionUsuario(ExcepcionUsuario.USUARIO_INCORRECTO);
		}

		Usuario usu = listaUsers.get(0);

		if (!usu.getPassword().equals(password)) {

			throw new ExcepcionUsuario(ExcepcionUsuario.PASSWORD_INCORRECTA);
		}

		session.setAttribute("usuarioLogueado", usu);

	}

}
