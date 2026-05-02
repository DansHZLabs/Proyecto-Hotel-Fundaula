package es.accenture.entity;                          //esto lo hace el A
													  //esta es la relación que hay que poner con habitacion
												  	  //@ManyToOne
													  //@JoinColumn(name = "id_habitacion")
													  //private Habitacion habitacion;
import java.util.Date;

public class Incidencia {
	
	public enum EstadoIncidencia {avisado,pendiente,arreglado} //clase interna para crear los enum y poner las opciones que se quiere establecer
	public enum PrioridadIncidencia {baja,media,alta} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	private int idIncidencia;
	
	private int idHabitacion;

	private EstadoIncidencia estadoIncidencia;
	
	private PrioridadIncidencia prioridadIncidencia;
	
	private String descripcionIncidencia;
	
	private Date fechaApertura;
	
	private Date fechaCierre;

	// Constructor vacío (es obligatorio)
	public Incidencia() {
		
	}
	
	// Constructor con parámetros (buena práctica, id no porque es autoincrement)
	public Incidencia(int idHabitacion,EstadoIncidencia estadoIncidencia,PrioridadIncidencia prioridadIncidencia,String descripcionIncidencia,Date fechaApertura,Date fechaCierre) {
		
		this.idHabitacion=idHabitacion;
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

	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}
	
	public int getIdHabitacion() {
		return idHabitacion;
	}
	
	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion=idHabitacion;
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
				+", ID habitación = "
				+idHabitacion
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