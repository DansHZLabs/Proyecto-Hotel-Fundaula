package es.accenture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad ORM (Object Relational Mapping) que mapea la tabla 'huespedes' de la
 * BBDD 'hoteldb'
 * 
 * @author danih y javi
 * @version 1.0
 */
@Entity // Anotacion para representar una tabla en la BBDD
@Table(name = "huespedes") // Anotacion que indica como se llama la tabla que representa
public class Huesped {

	// ATRIBUTOS

	@Id // Clave primaria INT
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Anotacion para generar automaticamente el ID en la base de datos (AUTO_INCREMENT)
	@Column(name = "id_huesped") // Anotacion que indica como se llama la columna que representa
	private int idHuesped;

	@Column(name = "nombre") // Anotacion que indica como se llama la columna que representa
	private String nombre;

	@Column(name = "apellidos") // Anotacion que indica como se llama la columna que representa
	private String apellidos;

	@Column(name = "direccion") // Anotacion que indica como se llama la columna que representa
	private String direccion;

	@Column(name = "telefono") // Anotacion que indica como se llama la columna que representa
	private String telefono;

	@Column(name = "email") // Anotacion que indica como se llama la columna que representa
	private String email;

	// CONSTRUCTOR VACIO (obligatorio)

	/**
	 * Genera un objeto de tipo huesped
	 */
	public Huesped() {

	}

	// CONSTRUCTOR POR PARAMETROS

	public Huesped(String nombre, String apellidos, String direccion, String telefono, String email) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;

	}

	// GETTERS Y SETTERS

	/**
	 * Metodo get que devuelve el campo 'id' de la tabla 'huespedes'
	 * @return idHuesped
	 */
	public int getIdHuesped() {
		return idHuesped;
	}

	/**
	 * Metodo get que devuelve el campo 'nombre' de la tabla 'huespedes'
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo
	 * 'nombre' de la tabla 'huespedes'
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo get que devuelve el campo 'apellidos' de la tabla 'huespedes'
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo
	 * 'apellidos' de la tabla 'huespedes'
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * Metodo get que devuelve el campo 'direccion' de la tabla 'huespedes'
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo
	 * 'direccion' de la tabla 'huespedes'
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Metodo get que devuelve el campo 'telefono' de la tabla 'huespedes'
	 * @return telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo
	 * 'telefono' de la tabla 'huespedes'
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * Metodo get que devuelve el campo 'email' de la tabla 'huespedes'
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Metodo set que asigna el valor que le pasemos como parametro al campo
	 * 'email' de la tabla 'huespedes'
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	// METODO toString
	
	
	/**
	 * Sobrecarga del toString para que devuelva texto en vez de la direccion de
	 * memoria del objeto
	 */
	@Override
	public String toString() {
		return "Huesped [idHuesped=" + idHuesped + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", telefono=" + telefono + ", email=" + email + "]";
	}
		
}
