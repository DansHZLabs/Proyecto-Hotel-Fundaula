package es.accenture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que mapea la tabla usarios de la BBDD 'hoteldb'
 * @author danih y javi
 * @Version 1.0
 *
 */
@Entity // Anotacion para representar una tabla en la BBDD
@Table(name = "usuarios") // Anotacion que indica como se llama la tabla que representa
public class Usuario {

	// ATRIBUTOS
	
	// Clase interna para crear los enum y poner las opciones que se quiere establecer
	public enum Rol {
		recepcionista, supervisor
	}

	@Id // Clave primaria INT
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Anotacion para generar automaticamente el ID en la base de datos (AUTO_INCREMENT)
	@Column(name = "id_usuario") // Anotacion que indica como se llama la tabla que representa
	private int id_usuario;

	@Column(name = "username") // Anotacion que indica como se llama la tabla que representa
	private String username;

	@Column(name = "password") // Anotacion que indica como se llama la tabla que representa
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "rol")
	private Rol rol;
	

	// CONSTRUCTOR VACIO (obligatorio)
	/**
	 * Genera un objeto de tipo usuario
	 */
	public Usuario() {
		
	}



	// CONSTRUCTOR POR PARAMETROS
	/**
	 * Genera un objeto de tipo usuario a partir de parametros (el id no se mete porque es autoincrement)
	 * @param username
	 * @param password
	 * @param rol
	 */
	public Usuario(String username, String password, Rol rol) {
		this.username = username;
		this.password = password;
		this.rol = rol;
	}


	// GETTERS Y SETTERS

	/**
	 * Metodo get que devuelve el campo 'id' de la tabla 'usuarios' 
	 * @return id_usuario
	 */
	public int getId_usuario() {
		return id_usuario;
	}


	/**
	 * Metodo get que devuelve el campo 'username' de la tabla 'usuarios' 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo 'username' de la tabla 'usuarios'
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * Metodo get que devuelve el campo 'password' de la tabla 'usuarios'
	 * @return password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo 'password' de la tabla 'usuarios'
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Metodo get que devuelve el campo 'rol' de la tabla 'usuarios'
	 * @return
	 */
	public Rol getRol() {
		return rol;
	}


	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo 'rol' de la tabla 'usuarios'
	 * @param rol
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}


	// METODO toString
	
	/**
	 * Sobrecarga del toString para que devuelva texto en vez de la direccion de memoria del objeto
	 */
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + ", rol="
				+ rol + "]";
	}
	
}
