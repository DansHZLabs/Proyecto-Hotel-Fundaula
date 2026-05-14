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

@Entity // Anotación para representar una tabla en BBDD
@Table(name="incidencias") // Anotación que indica cómo se llama la tabla que representa
public class Incidencia {
	
	public enum EstadoIncidencia {abierta,en_curso,cerrada} //clase interna para crear los enum y poner las opciones que se quiere establecer
	public enum PrioridadIncidencia {baja,media,alta} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY)//Anotación para generar automáticamente el ID en la base de datos (AUTO_INCREMENT)
	@Column(name = "id_incidencia",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido INT
	private int idIncidencia;
	
	@ManyToOne(fetch=FetchType.EAGER) //Anotación para la relación 1N con habitación 1 habitación puede tener muchas incidencias y Lazy porque se dan datos bajo demanda, se cambia a EAGER porque con LAZY no va porque se limita
	@JoinColumn(name="id_habitacion",nullable=false) //Anotación que indica que se referencia a Habitacion es clave FK y que es requerido INT
	private Habitacion habitacion; //Se crea el objeto Habitación para hacerlo con anotaciones en las relaciones

	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings
	@Column(name="estado_incidencia",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido ENUM
	private EstadoIncidencia estadoIncidencia;
	
	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings ENUM
	@Column(name="prioridad",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido ENUM
	private PrioridadIncidencia prioridadIncidencia;
	
	@Column(name="descripcion_incidencia") //Anotación que indica cómo se llama la tabla que representa TEXT
	private String descripcionIncidencia;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_apertura",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido DATE
	private Date fechaApertura;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_cierre") //Anotación que indica cómo se llama la tabla que representa DATE
	private Date fechaCierre;

	// Constructor vacío (es obligatorio)
	public Incidencia() {
		
	}
	
	// Constructor con parámetros (buena práctica, id no se pone porque es autoincrement)
	public Incidencia(Habitacion habitacion,EstadoIncidencia estadoIncidencia,PrioridadIncidencia prioridadIncidencia,String descripcionIncidencia,Date fechaApertura,Date fechaCierre) {
		
		this.habitacion=habitacion;
		this.estadoIncidencia=estadoIncidencia;
		this.prioridadIncidencia=prioridadIncidencia;
		this.descripcionIncidencia=descripcionIncidencia;
		this.fechaApertura=fechaApertura;
		this.fechaCierre=fechaCierre;
	
	}

	//getters y setters
	public int getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(int idIncidencia) { //se quita porque es autoincremental, se vuelve a poner porque sino al rellenar el formulario no puede hacerlo y crea una nueva
		this.idIncidencia = idIncidencia;
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}
	
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion=habitacion;
	}

	public EstadoIncidencia getEstadoIncidencia() {
		return estadoIncidencia;
	}

	public void setEstadoIncidencia(EstadoIncidencia estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}

	public PrioridadIncidencia getPrioridadIncidencia() {
		return prioridadIncidencia;
	}

	public void setPrioridadIncidencia(PrioridadIncidencia prioridadIncidencia) {
		this.prioridadIncidencia = prioridadIncidencia;
	}

	public String getDescripcionIncidencia() {
		return descripcionIncidencia;
	}

	public void setDescripcionIncidencia(String descripcionIncidencia) {
		this.descripcionIncidencia = descripcionIncidencia;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	// toString para mostrar como texto
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