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

/**
 * Clase entidad que representa una reserva registrada en el hotel.
 * 
 * Esta clase almacena la informacion de las reservas,
 * incluyendo el huesped, la habitacion, las fechas de estancia,
 * el tipo de pension y el estado de la reserva.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Entity // Anotación para representar una tabla en BBDD
@Table(name="reservas") // Anotación que indica cómo se llama la tabla que representa
public class Reserva {
	
	/**
	 * Enumeracion que define los posibles estados de una reserva. ENUM
	 */
	public enum EstadoReserva {PENDIENTE,CONFIRMADA,CANCELADA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	/**
	 * Enumeracion que define los distintos tipos de pension disponibles. ENUM
	 */
	public enum TipoPension {ALOJAMIENTO,MEDIA,COMPLETA} //clase interna para crear los enum y poner las opciones que se quiere establecer
	
	/*
	 * Identificador unico de la reserva.
	 */
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY) //GenerateValue dice a BBDD que genere ID automático cada vez que guardemos una reserva con autoincrement que es IDENTITY INT
	@Column(name="id_reserva",nullable=false) //Anotación para decir a Spring que la columna es id_reserva y no puede estar vacía ni repetirse INT
	private int idReserva;
	
	/*
	 * Huesped de la reserva.
	 */
	@ManyToOne(fetch=FetchType.LAZY) //Anotación para la relación N1 con huespec, muchas reservas pueden ser de un solo huesped, Lazy porque se dan datos bajo demanda y sino sale por defecto EAGER por ser ManyToOne
	@JoinColumn(name="id_huesped",nullable=false) //Anotación para decir a Spring a que tabla referencia y que es requerido INT
	private Huesped huesped;
	
	/*
	 * Habitacion de la reserva.
	 */
	@ManyToOne(fetch=FetchType.LAZY) //Anotación para la relación N1 con habitación, muchas reservas pueden estar asociadas a una sola habitación, Lazy porque se dan datos bajo demanda y sino sale por defecto EAGER por ser ManyToOne
	@JoinColumn(name="id_habitacion",nullable=false) //Anotación para decir a Spring a que tabla referencia y que es requerido INT
	private Habitacion habitacion;
	
	/*
	 * Fecha de entrada de la reserva.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_entrada",nullable=false) //Anotación para decir a Spring que la columna es fecha_entrada y no puede estar vacía ni repetirse DATE
	private Date fechaEntrada;
	
	/*
	 * Fecha de salida de la reserva.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") //Anotación para el formato de la fecha porque sino spring no la reconoce como fecha
	@Column(name="fecha_salida",nullable=false) //Anotación para decir a Spring que la columna es fecha_salida y no puede estar vacía ni repetirse DATE
	private Date fechaSalida;
	
	/*
	 * Tipo de pension de la reserva.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pension") //Anotación para decir a Spring que la columna es tipo_pension ENUM
	private TipoPension tipoPension;
	
	/*
	 * Estado actual de la reserva.
	 */
	@Enumerated(EnumType.STRING) //Anotación para los enum, le dice a BBDD que son strings
	@Column(name="estado_reserva") //Anotación para decir a Spring que la columna es estado_reserva ENUM
	private EstadoReserva estadoReserva;
	
	/*
	 * Numero de huespedes de la reserva.
	 */
	@Column(name="numero_huespedes",nullable=false) //Anotación para decir a Spring que la columna es numero_huespedes y no puede repetirse INT
	private int numeroHuespedes;
	
	/*
	 * Observaciones de la reserva.
	 */
	@Column(name="observaciones")  //Anotación para decir a Spring que la columna es observaciones TEXT
	private String observaciones; //aunque coincida el nombre y no haga falta Israel dice en el video que la buena práctica es ponerlo
	
	/**
	 * Constructor vacio, es obligatorio.
	 */
	public Reserva() {
		
	}
	
	/**
	 * Constructor por parametros para crear una reserva
	 * 
	 * @param huesped          huesped de la reserva
	 * @param habitacion       habitacion de la reserva
	 * @param fechaEntrada     fecha de entrada de la reserva
	 * @param fechaSalida      fecha de salida de la reserva
	 * @param tipoPension      tipo de pension de la reserva
	 * @param estadoReserva    estado actual de la reserva
	 * @param numeroHuespedes  numero de huespedes de la reserva
	 * @param observaciones    observaciones de la reserva
	 */
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

	/**
	 * Metodo que obtiene el id de la reserva.
	 * 
	 * @return id de la reserva
	 */
	public int getIdReserva() {
		return idReserva;
	}

	/**
	 * Metodo que establece el id de la reserva.
	 * 
	 * @param idReserva id de la reserva
	 */
	public void setIdReserva(int idReserva) { //se quita el setter porque es autoincremental y se pone solo
		this.idReserva = idReserva;
	}

	/**
	 * Metodo que obtiene el huesped de la reserva.
	 * 
	 * @return huesped de la reserva
	 */
	public Huesped getHuesped() {
		return huesped;
	}

	/**
	 * Metodo que establece el huesped de la reserva.
	 * 
	 * @param huesped huesped de la reserva
	 */
	public void setHuesped(Huesped huesped) { //este establece la relacion con Huesped
		this.huesped = huesped;
	}
	
	/**
	 * Metodo que obtiene la habitacion de la reserva.
	 * 
	 * @return habitacion de la reserva
	 */
	public Habitacion getHabitacion() {
		return habitacion;
	}

	/**
	 * Metodo que establece la habitacion de la reserva.
	 * 
	 * @param habitacion habitacion de la reserva
	 */
	public void setHabitacion(Habitacion habitacion) { //este setter establece la relacion con Habitacion
		this.habitacion = habitacion;
	}

	/**
	 * Metodo que obtiene la fecha de entrada de la reserva.
	 * 
	 * @return fecha de entrada de la reserva
	 */
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	/**
	 * Metodo que establece la fecha de entrada de la reserva.
	 * 
	 * @param fechaEntrada fecha de entrada de la reserva
	 */
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	/**
	 * Metodo que obtiene la fecha de salida de la reserva.
	 * 
	 * @return fecha de salida de la reserva
	 */
	public Date getFechaSalida() {
		return fechaSalida;
	}

	/**
	 * Metodo que establece la fecha de salida de la reserva.
	 * 
	 * @param fechaSalida fecha de salida de la reserva
	 */
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/**
	 * Metodo que obtiene el tipo de pension de la reserva.
	 * 
	 * @return tipo de pension de la reserva
	 */
	public TipoPension getTipoPension() {
		return tipoPension;
	}

	/**
	 * Metodo que establece el tipo de pension de la reserva.
	 * 
	 * @param tipoPension tipo de pension de la reserva
	 */
	public void setTipoPension(TipoPension tipoPension) {
	    this.tipoPension = tipoPension;
	}
	
	/**
	 * Metodo que obtiene el estado de la reserva.
	 * 
	 * @return estado de la reserva
	 */
	public EstadoReserva getEstadoReserva() {
	    return estadoReserva;
	}
	
	/**
	 * Metodo que establece el estado de la reserva.
	 * 
	 * @param estadoReserva estado de la reserva
	 */
	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	/**
	 * Metodo que obtiene el numero de huespedes de la reserva.
	 * 
	 * @return numero de huespedes de la reserva
	 */
	public int getNumeroHuespedes() {
		return numeroHuespedes;
	}

	/**
	 * Metodo que establece el numero de huespedes de la reserva.
	 * 
	 * @param numeroHuespedes numero de huespedes de la reserva
	 */
	public void setNumeroHuespedes(int numeroHuespedes) {
		this.numeroHuespedes = numeroHuespedes;
	}
	
	/**
	 * Metodo que obtiene las observaciones de la reserva.
	 * 
	 * @return observaciones de la reserva
	 */
	public String getObservaciones() {
	    return observaciones;
	}
	
	/**
	 * Metodo que establece las observaciones de la reserva.
	 * 
	 * @param observaciones observaciones de la reserva
	 */
	public void setObservaciones(String observaciones) {
	    this.observaciones = observaciones;
	}
	
	 /**
     * Metodo para mostrar como texto.
     * 
     * @return cadena de texto con la informacion de la reserva
     */
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
