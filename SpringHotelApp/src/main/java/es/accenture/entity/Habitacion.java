
package es.accenture.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase entidad que representa una habitacion del hotel.
 * 
 * Esta clase guarda la informacion de las habitaciones, su numero, tipo,
 * disponibilidad, orientacion y relaciones con incidencias y reservas.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Entity
@Table(name="habitaciones")
public class Habitacion {

	/**
	 * Enumeracion que define los distintos tipos de habitacion disponibles.ENUM
	 */
    public enum Tipo {
        INDIVIDUAL,DOBLE,SUITE
    }

    /**
     * Enumeracion que define los distintos estados de disponibilidad. ENUM
     * de una habitacion.
     */
    public enum Disponibilidad {
        DISPONIBLE,OCUPADA,LIMPIEZA,MANTENIMIENTO
    }
    
    /**
     * Enumeracion que define la orientacion de la habitacion. ENUM
     */
    public enum Orientacion {
    	INTERIOR,EXTERIOR
    }

    /*
     * Identificador unico de la habitacion.PK
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_habitacion",nullable=false)
    private int idHabitacion;

    /*
     * Numero identificativo de la habitacion, no es el id.
     */
    @Column(name="numero_habitacion",nullable=false,unique=true)
    private String numeroHabitacion;

    /*
     * Tipo de habitacion.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="tipo_habitacion",nullable=false)
    private Tipo tipo;

    /*
     * Precio por noche de la habitacion.
     */
    @Column(name="precio_noche",nullable=false)
    private BigDecimal precioPorNoche;

    /*
     * Estado de disponibilidad de la habitacion.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="disponibilidad_habitacion",nullable=false)
    private Disponibilidad disponibilidad;

    /*
     * Orientacion de la habitacion.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="orientacion_habitacion")
    private Orientacion orientacionHabitacion;
    
    /*
     * Lista de incidencias de la habitacion.
     */
    @OneToMany(mappedBy="habitacion",fetch=FetchType.LAZY)  
    private List<Incidencia>incidencias;
    
    /*
     * Lista de reservas de la habitacion.
     */
    @OneToMany(mappedBy = "habitacion",fetch=FetchType.LAZY)
    private List<Reserva> reservas;
    
    /**
     * Constructor vacio, es obligatorio.
     */
    public Habitacion() {
    }

    /**
     * Constructor por parametros para crear una habitacion
     * 
     * @param numeroHabitacion     numero identificativo de la habitacion
     * @param tipo                 tipo de habitacion
     * @param precioPorNoche       precio por noche de la habitacion
     * @param disponibilidad       estado de disponibilidad de la habitacion
     * @param orientacionHabitacion orientacion de la habitacion
     */
    public Habitacion(String numeroHabitacion, Tipo tipo,
                      BigDecimal precioPorNoche, Disponibilidad disponibilidad,
                      Orientacion orientacionHabitacion) {

        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponibilidad = disponibilidad;
        this.orientacionHabitacion = orientacionHabitacion;
    }

    /**
     * Metodo que obtiene el id de la habitacion.
     * 
     * @return id de la habitacion
     */
    //getters y setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    /**
     * Metodo que establece el id de la habitacion.
     * 
     * @param idHabitacion id de la habitacion
     */
    public void setIdHabitacion(int idHabitacion) { //el setter se quita porque es autoincremental, luego se vuelve a poner porque sino no puede cogerlo para editar
        this.idHabitacion = idHabitacion;
    }

    /**
     * Metodo que obtiene el numero de la habitacion.
     * 
     * @return numero de la habitacion
     */
    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    /**
     * Metodo que establece el numero de la habitacion.
     * 
     * @param numeroHabitacion numero de la habitacion
     */
    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    /**
     * Metodo que obtiene el tipo de habitacion.
     * 
     * @return tipo de habitacion
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Metodo que establece el tipo de habitacion.
     * 
     * @param tipo tipo de habitacion
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Metodo que obtiene el precio por noche de la habitacion.
     * 
     * @return precio por noche de la habitacion
     */
    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    /**
     * Metodo que establece el precio por noche de la habitacion.
     * 
     * @param precioPorNoche precio por noche de la habitacion
     */
    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    /**
     * Metodo que obtiene el estado de disponibilidad de la habitacion.
     * 
     * @return estado de disponibilidad de la habitacion
     */
    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * Metodo que establece el estado de disponibilidad de la habitacion.
     * 
     * @param disponibilidad estado de disponibilidad de la habitacion
     */
    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Metodo que obtiene la orientacion de la habitacion.
     * 
     * @return orientacion de la habitacion
     */
    public Orientacion getOrientacionHabitacion() {
        return orientacionHabitacion;
    }

    /**
     * Metodo que establece la orientacion de la habitacion.
     * 
     * @param orientacionHabitacion orientacion de la habitacion
     */
    public void setOrientacionHabitacion(Orientacion orientacionHabitacion) {
        this.orientacionHabitacion = orientacionHabitacion;
    }
    
    /**
     * Metodo que obtiene la lista de incidencias de la habitacion.
     * 
     * @return lista de incidencias de la habitacion
     */
    public List<Incidencia>getIncidencias() {
        return incidencias;
    }

    /**
     * Metodo que establece la lista de incidencias de la habitacion.
     * 
     * @param incidencias lista de incidencias de la habitacion
     */
    public void setIncidencias(List<Incidencia>incidencias) {
        this.incidencias = incidencias;
    }
    
    /**
     * Metodo que obtiene la lista de reservas de la habitacion.
     * 
     * @return lista de reservas de la habitacion
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Metodo que establece la lista de reservas de la habitacion.
     * 
     * @param reservas lista de reservas de la habitacion
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Metodo para mostrar como texto.
     * 
     * @return cadena de texto con la informacion de la habitacion
     */
    @Override
    public String toString() {
        return "Habitacion [ID Habitación=" + idHabitacion +
                ", Numero=" + numeroHabitacion +
                ", Tipo=" + tipo +
                ", Precio=" + precioPorNoche +
                ", Disponibilidad=" + disponibilidad +
                ", Orientación=" + orientacionHabitacion +
                "]";
    }
    
}
