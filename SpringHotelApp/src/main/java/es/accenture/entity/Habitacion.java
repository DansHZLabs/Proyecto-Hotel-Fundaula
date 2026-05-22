
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

@Entity
@Table(name="habitaciones")
public class Habitacion {

    public enum Tipo {
        INDIVIDUAL,DOBLE,SUITE
    }

    public enum Disponibilidad {
        DISPONIBLE,OCUPADA,LIMPIEZA,MANTENIMIENTO
    }
    
    public enum Orientacion {
    	INTERIOR,EXTERIOR
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_habitacion",nullable=false)
    private int idHabitacion;

    @Column(name="numero_habitacion",nullable=false,unique=true)
    private String numeroHabitacion;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_habitacion",nullable=false)
    private Tipo tipo;

    @Column(name="precio_noche",nullable=false)
    private BigDecimal precioPorNoche;

    @Enumerated(EnumType.STRING)
    @Column(name="disponibilidad_habitacion",nullable=false)
    private Disponibilidad disponibilidad;

    @Enumerated(EnumType.STRING)
    @Column(name="orientacion_habitacion")
    private Orientacion orientacionHabitacion;
    
    @OneToMany(mappedBy="habitacion",fetch=FetchType.LAZY)  
    private List<Incidencia>incidencias;
    
    @OneToMany(mappedBy = "habitacion",fetch=FetchType.LAZY)
    private List<Reserva> reservas;
    
    // Constructor vacío
    public Habitacion() {
    }

    // Constructor con parámetros
    public Habitacion(String numeroHabitacion, Tipo tipo,
                      BigDecimal precioPorNoche, Disponibilidad disponibilidad,
                      Orientacion orientacionHabitacion) {

        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponibilidad = disponibilidad;
        this.orientacionHabitacion = orientacionHabitacion;
    }

    //getters y setters
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) { //el setter se quita porque es autoincremental, luego se vuelve a poner porque sino no puede cogerlo para editar
        this.idHabitacion = idHabitacion;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(BigDecimal precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Orientacion getOrientacionHabitacion() {
        return orientacionHabitacion;
    }

    public void setOrientacionHabitacion(Orientacion orientacionHabitacion) {
        this.orientacionHabitacion = orientacionHabitacion;
    }
    
    public List<Incidencia>getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia>incidencias) {
        this.incidencias = incidencias;
    }
    
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    // toString para mostrar como texto
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
