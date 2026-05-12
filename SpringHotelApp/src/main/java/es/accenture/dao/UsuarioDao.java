package es.accenture.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.accenture.entity.Usuario;
import es.accenture.interfaces.InUsuarioDAO;

/**
 * Clase DAO (Data Acces Object) encargada de buscar en la BBDD 'hoteldb' la
 * credencial de usuario (enviada en la request a partir del formulario del
 * 'Login.jsp'), y devolver los datos correspondientes en caso de coincidencia.
 * Implementa la interfaz InUsuarioDAO para generar un mayor desacoplamiento por
 * si en un futuro se quiere utilizar otra clase DAO en vez de esta (y asi tener
 * que cambiar menos cantidad de codigo)*
 * 
 * @author danih y javi
 * @version 1.0
 */
@Component // Anotacion de Spring para mapear la clase como componente (y asi crear un bean que este disponible cuando se necesite)			 
public class UsuarioDao implements InUsuarioDAO {

	private SessionFactory sessionFactory; // Atributo donde se almacena la conexion con la BBDD

	/**
	 * Constructor por parametros en la que se usa la inyeccion de dependencias del
	 * SessionFactory. Se hace aqui y no en el atributo para que Spring cree al
	 * mismo tiempo el objeto de tipo UsuarioDao y su dependencia SessionFactory,
	 * evitando un NullPointerException al crear en primera instancia un
	 * SessionFactory null
	 * 
	 * @param sessionFactory
	 */
	@Autowired // Etiqueta de Spring para la inyeccion de dependencias al detectar una clase como 'component' o similar en el paquete seleccionado en la configuracion.				 
	public UsuarioDao(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Transactional // Etiqueta de Spring para crear y cerrar de forma automatica las Transacciones (se crea la SessionFactory, se abre una sesion y se inicia la transaccion)					 
	@Override // Se implementa el metodo de la interfaz InUsuarioDAO
	public List<Usuario> obtenerUsuario(String usuario) {

		/**
		 * El return devuelve la lista de usuarios obtenidos de hoteldb (tabla usuarios,
		 * pero en HQL se mapea con el nombre de la clase ORM, por eso Usuario en
		 * singular) con respecto a con la credencial que le pasemos como parametro en
		 * este metodo. El get currentSession se utiliza porque se obtiene la sesion que
		 * se ha generado a partir de la etiqueta Transactional (sino habría que crearla
		 * manualmente '.openSession'). Respecto al ":username" esos dos puntos hacen
		 * referencia a que se utilizara una prepared statement que luego inyectaremos
		 * con el .setParameter (en este caso el String de tipo usuario). Esto ultimo se
		 * ha investigado con la IA 		 */
		return sessionFactory.getCurrentSession()
				.createQuery("from Usuario u where u.username = :username", Usuario.class) // IA: uso de ':' como prepared Statement sugerido por Gemini ya que es mejor que utilizar un ? por terminos de practicidad (para poder cambiar los prepared Statement, si hay varios, en el orden del codigo sin que esto afecte luego a su declaracion)
				.setParameter("username", usuario).getResultList();

	}

}
