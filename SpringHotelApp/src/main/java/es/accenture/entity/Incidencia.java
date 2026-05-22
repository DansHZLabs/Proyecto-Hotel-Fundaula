package es.accenture.entity;                          //esto lo hace el A
													  //esta es la relación que hay que poner con habitacion
												  	  //@ManyToOne
													  //@JoinColumn(name = "id_habitacion")
													  //private Habitacion habitacion;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase entidad que representa una incidencia.
 * 
 * Esta clase almacena la informacion de las incidencias
 * de las habitaciones, incluyendo su estado, prioridad, descripcion
 * y fechas de apertura y fecha decierre.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Entity // Anotación para representar una tabla en BBDD
@Table(name="incidencias") // Anotación que indica cómo se llama la tabla que representa
public class Incidencia {
	
	/**
	 * Enumeracion que define los posibles estados de una incidencia. ENUM
	 */
	public enum EstadoIncidencia {ABIERTA,EN_CURSO,CERRADA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	/**
	 * Enumeracion que define los niveles de prioridad de una incidencia. ENUM
	 */
	public enum PrioridadIncidencia {BAJA,MEDIA,ALTA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	/*
	 * Identificador unico de la incidencia.
	 */
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Anotación para generar automáticamente el ID en la base de datos (AUTO_INCREMENT)
	@Column(name = "id_incidencia",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido INT
	private int idIncidencia;
	
	/*
	 * Habitacion de la incidencia.
	 */
	@ManyToOne(fetch=FetchType.EAGER) //Anotación para la relación 1N con habitación 1 habitación puede tener muchas incidencias y Lazy porque se dan datos bajo demanda, se cambia a EAGER porque con LAZY no va porque se limita
	@JoinColumn(name="id_habitacion",nullable=false) //Anotación que indica que se referencia a Habitacion es clave FK y que es requerido INT
	private Habitacion habitacion; //Se crea el objeto Habitación para hacerlo con anotaciones en las relaciones

	/*
	 * Estado actual de la incidencia.
	 */
	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings
	@Column(name="estado_incidencia",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido ENUM
	private EstadoIncidencia estadoIncidencia;
	
	/*
	 * Prioridad de la incidencia.
	 */
	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings ENUM
	@Column(name="prioridad",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido ENUM
	private PrioridadIncidencia prioridadIncidencia;
	
	/*
	 * Descripcion de la incidencia.
	 */
	@Column(name="descripcion_incidencia") //Anotación que indica cómo se llama la tabla que representa TEXT
	private String descripcionIncidencia;
	
	/*
	 * Fecha de apertura de la incidencia.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_apertura",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido DATE
	private Date fechaApertura;
	
	/*
	 * Fecha de cierre de la incidencia.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_cierre") //Anotación que indica cómo se llama la tabla que representa DATE
	private Date fechaCierre;

	/**
	 * Constructor vacio, es obligatorio.
	 */
	public Incidencia() {
		
	}
	
	/**
	 * Constructor por parametros para crear una incidencia
	 * 
	 * @param habitacion            habitacion de la incidencia
	 * @param estadoIncidencia      estado actual de la incidencia
	 * @param prioridadIncidencia   prioridad de la incidencia
	 * @param descripcionIncidencia descripcion de la incidencia
	 * @param fechaApertura         fecha de apertura de la incidencia
	 * @param fechaCierre           fecha de cierre de la incidencia
	 */
	public Incidencia(Habitacion habitacion,EstadoIncidencia estadoIncidencia,PrioridadIncidencia prioridadIncidencia,String descripcionIncidencia,Date fechaApertura,Date fechaCierre) {
		
		this.habitacion=habitacion;
		this.estadoIncidencia=estadoIncidencia;
		this.prioridadIncidencia=prioridadIncidencia;
		this.descripcionIncidencia=descripcionIncidencia;
		this.fechaApertura=fechaApertura;
		this.fechaCierre=fechaCierre;
	
	}

	/**
	 * Metodo que obtiene el id de la incidencia.
	 * 
	 * @return id de la incidencia
	 */
	//getters y setters
	public int getIdIncidencia() {
		return idIncidencia;
	}

	/**
	 * Metodo que establece el id de la incidencia.
	 * 
	 * @param idIncidencia id de la incidencia
	 */
	public void setIdIncidencia(int idIncidencia) { //se quita porque es autoincremental, se vuelve a poner porque sino al rellenar el formulario no puede hacerlo y crea una nueva
		this.idIncidencia = idIncidencia;
	}
	
	/**
	 * Metodo que obtiene la habitacion de la incidencia.
	 * 
	 * @return habitacion asociada a la incidencia
	 */
	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	/**
	 * Metodo que establece la habitacion de la incidencia.
	 * 
	 * @param habitacion habitacion de la incidencia
	 */
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion=habitacion;
	}

	/**
	 * Metodo que obtiene el estado de la incidencia.
	 * 
	 * @return estado de la incidencia
	 */
	public EstadoIncidencia getEstadoIncidencia() {
		return estadoIncidencia;
	}

	/**
	 * Metodo que establece el estado de la incidencia.
	 * 
	 * @param estadoIncidencia estado de la incidencia
	 */
	public void setEstadoIncidencia(EstadoIncidencia estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}

	/**
	 * Metodo que obtiene la prioridad de la incidencia.
	 * 
	 * @return prioridad de la incidencia
	 */
	public PrioridadIncidencia getPrioridadIncidencia() {
		return prioridadIncidencia;
	}

	/**
	 * Metodo para la prioridad de la incidencia.
	 * 
	 * @param prioridadIncidencia prioridad de la incidencia
	 */
	public void setPrioridadIncidencia(PrioridadIncidencia prioridadIncidencia) {
		this.prioridadIncidencia = prioridadIncidencia;
	}

	/**
	 * Metodo que obtiene la descripcion de la incidencia.
	 * 
	 * @return descripcion de la incidencia
	 */
	public String getDescripcionIncidencia() {
		return descripcionIncidencia;
	}

	/**
	 * Metodo que establece la descripcion de la incidencia.
	 * 
	 * @param descripcionIncidencia descripcion de la incidencia
	 */
	public void setDescripcionIncidencia(String descripcionIncidencia) {
		this.descripcionIncidencia = descripcionIncidencia;
	}

	/**
	 * Metodo que obtiene la fecha de apertura de la incidencia.
	 * 
	 * @return fecha de apertura de la incidencia
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Metodo que establece la fecha de apertura de la incidencia.
	 * 
	 * @param fechaApertura fecha de apertura de la incidencia
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * Metodo que obtiene la fecha de cierre de la incidencia.
	 * 
	 * @return fecha de cierre de la incidencia
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Metodo que establece la fecha de cierre de la incidencia.
	 * 
	 * @param fechaCierre fecha de cierre de la incidencia
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	 /**
     * Metodo para mostrar como texto.
     * 
     * @return cadena de texto con la informacion de la incidencia
     */
	@Override
	public String toString() {
		return "Incidencia [ID incidencia= "
				+idIncidencia
				+", Habitación = "
				+habitacion.getIdHabitacion()
				+", Estado incidencia= "
				+estadoIncidencia
				+", Prioridad incidencia= "
				+prioridadIncidencia
				+", Descripcion incidencia= "
				+descripcionIncidencia
				+", Fecha de Apertura = "
				+fechaApertura
				+", Fecha de Cierre = "
				+fechaCierre
				+"]";
		
	}
	
}