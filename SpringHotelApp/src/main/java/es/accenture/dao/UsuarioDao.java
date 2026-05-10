package es.accenture.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.accenture.entity.Usuario;

/**
 * 
 * @author danih y javi
 * @version 1.0
 */
@Component
public class UsuarioDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Usuario> obtenerUsuario(String usuario, String password){
		
		return sessionFactory.getCurrentSession()
									.createQuery("from Usuario u where u.username = :username",Usuario.class)
									.setParameter("username", usuario)
									.getResultList();
		
		
	}

}
