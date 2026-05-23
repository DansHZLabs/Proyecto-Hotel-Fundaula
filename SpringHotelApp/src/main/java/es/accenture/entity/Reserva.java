package es.accenture.entity;         

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
 * Entidad ORM (Object Relational Mapping) que mapea la tabla 'reservas' de la
 * BBDD 'hoteldb'
 * @author danih y javi
 * @version 1.0
 *
 */
@Entity // Anotación para representar una tabla en BBDD
@Table(name="reservas") // Anotación que indica cómo se llama la tabla que representa
public class Reserva {
	
	/**
	 * Enumeracion que define los posibles estados de una reserva. ENUM
	 */
	public enum EstadoReserva {PENDIENTE,CONFIRMADA,CANCELADA}
	
	/**
	 * Enumeracion que define los distintos tipos de pension disponibles. ENUM
	 */
	public enum TipoPension {ALOJAMIENTO,MEDIA,COMPLETA} 
	
	/*
	 * Identificador unico de la reserva.
	 */
	@Id //clave primaria INT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_reserva",nullable=false)
	private int idReserva;
	
	/*
	 * Huesped de la reserva.
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_huesped",nullable=false) 
	private Huesped huesped;
	
	/*
	 * Habitacion de la reserva.
	 */
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="id_habitacion",nullable=false) 
	private Habitacion habitacion;
	
	/*
	 * Fecha de entrada de la reserva.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_entrada",nullable=false)
	private Date fechaEntrada;
	
	/*
	 * Fecha de salida de la reserva.
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_salida",nullable=false)
	private Date fechaSalida;
	
	/*
	 * Tipo de pension de la reserva.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_pension")
	private TipoPension tipoPension;
	
	/*
	 * Estado actual de la reserva.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="estado_reserva")
	private EstadoReserva estadoReserva;
	
	/*
	 * Numero de huespedes de la reserva.
	 */
	@Column(name="numero_huespedes",nullable=false)
	private int numeroHuespedes;
	
	/*
	 * Observaciones de la reserva.
	 */
	@Column(name="observaciones")
	private String observaciones;
	
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
	public void setIdReserva(int idReserva) {
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
	public void setHuesped(Huesped huesped) {
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
	public void setHabitacion(Habitacion habitacion) {
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
