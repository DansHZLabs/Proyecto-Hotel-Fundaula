package es.accenture.entity;                          //esto lo hace cualquiera entero A o B

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
@Table(name="reservas") // Anotación que indica cómo se llama la tabla que representa
public class Reserva {
	
	public enum EstadoReserva {PENDIENTE,CONFIRMADA,CANCELADA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	public enum TipoPension {ALOJAMIENTO,MEDIA,COMPLETA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY) //GenerateValue dice a BBDD que genere ID automático cada vez que guardemos una reserva con autoincrement que es IDENTITY INT
	@Column(name="id_reserva",nullable=false) //Anotación para decir a Spring que la columna es id_reserva y no puede estar vacía ni repetirse INT
	private int idReserva;
	
	@ManyToOne(fetch=FetchType.LAZY) //Anotación para la relación N1 con huespec, muchas reservas pueden ser de un solo huesped, Lazy porque se dan datos bajo demanda y sino sale por defecto EAGER por ser ManyToOne
	@JoinColumn(name="id_huesped",nullable=false) //Anotación para decir a Spring a que tabla referencia y que es requerido INT
	private Huesped huesped;
	
	@ManyToOne(fetch=FetchType.LAZY) //Anotación para la relación N1 con habitación, muchas reservas pueden estar asociadas a una sola habitación, Lazy porque se dan datos bajo demanda y sino sale por defecto EAGER por ser ManyToOne
	@JoinColumn(name="id_habitacion",nullable=false) //Anotación para decir a Spring a que tabla referencia y que es requerido INT
	private Habitacion habitacion;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_entrada",nullable=false) //Anotación para decir a Spring que la columna es fecha_entrada y no puede estar vacía ni repetirse DATE
	private Date fechaEntrada;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_salida",nullable=false) //Anotación para decir a Spring que la columna es fecha_salida y no puede estar vacía ni repetirse DATE
	private Date fechaSalida;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pension") //Anotación para decir a Spring que la columna es tipo_pension ENUM
	private TipoPension tipoPension;
	
	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings
	@Column(name="estado_reserva") //Anotación para decir a Spring que la columna es estado_reserva ENUM
	private EstadoReserva estadoReserva;
	
	@Column(name="numero_huespedes",nullable=false) //Anotación para decir a Spring que la columna es numero_huespedes y no puede repetirse INT
	private int numeroHuespedes;
	
	@Column(name="observaciones")  //Anotación para decir a Spring que la columna es observaciones TEXT
	private String observaciones; //aunque coincida el nombre y no haga falta Israel dice en el video que la buena práctica es ponerlo
	
	public Reserva() {
		
	}
	
	public Reserva(Huesped huesped,Habitacion habitacion,Date fechaEntrada,Date fechaSalida,TipoPension tipoPension,EstadoReserva estadoReserva,int numeroHuespedes,String observaciones) {
		
		//se quita id_reserva porque es autoincremental
		
		this.huesped=huesped;
		this.habitacion=habitacion;
		this.fechaEntrada=fechaEntrada;
		this.fechaSalida=fechaSalida;
		this.tipoPension=tipoPension;
		this.estadoReserva=estadoReserva;
		this.numeroHuespedes=numeroHuespedes;
		this.observaciones=observaciones;
	}

	public int getIdReserva() {
		return idReserva;
	}

	/*public void setIdReserva(int idReserva) { //se quita el setter porque es autoincremental y se pone solo
		this.idReserva = idReserva;
	}*/

	public Huesped getHuesped() {
		return huesped;
	}

	public void setHuesped(Huesped huesped) { //este establece la relacion con Huesped
		this.huesped = huesped;
	}
	
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) { //este setter establece la relacion con Habitacion
		this.habitacion = habitacion;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public TipoPension getTipoPension() {
		return tipoPension;
	}

	public void setTipoPension(TipoPension tipoPension) {
	    this.tipoPension = tipoPension;
	}
	
	public EstadoReserva getEstadoReserva() {
	    return estadoReserva;
	}
	
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public int getNumeroHuespedes() {
		return numeroHuespedes;
	}

	public void setNumeroHuespedes(int numeroHuespedes) {
		this.numeroHuespedes = numeroHuespedes;
	}
	
	public String getObservaciones() {
	    return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
	    this.observaciones = observaciones;
	}
	
	@Override
	public String toString() {
		return "Reserva [ID Reserva = "
				+ idReserva
				+ ", Huesped = "
				+ huesped.getIdHuesped()
				+ ", Habitación = "
				+ habitacion.getIdHabitacion()
				+ ", Fecha de Entrada = "
				+ fechaEntrada
				+ ", Fecha de Salida = "
				+ fechaSalida
				+ ", Tipo de Pensión = "
				+ tipoPension
				+ ", Número de Huespedes = "
				+ numeroHuespedes
				+ ", Observaciones = "
				+ observaciones
				+ "]";
		
	}

}
