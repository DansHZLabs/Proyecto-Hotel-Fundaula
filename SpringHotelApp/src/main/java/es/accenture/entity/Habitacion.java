package es.accenture.entity;           

import java.math.BigDecimal;
import java.util.List;

public class Habitacion {

	//clase interna para crear los enum y poner las opciones que se quiere establecer
    public enum Tipo {
        individual,doble,suite
    }

    //clase interna para crear los enum y poner las opciones que se quiere establecer
    public enum Disponibilidad {
        disponible,ocupada,limpieza,mantenimiento
    }
    
    //clase interna para crear los enum y poner las opciones que se quiere establecer
    public enum Orientacion {
    	interior,exterior
    }

    private int id;

    private String numeroHabitacion;

    private Tipo tipo;

    private BigDecimal precioPorNoche;

    private Disponibilidad disponibilidad;

    private Orientacion orientacionHabitacion;
    
    private List<Incidencia>incidencias; //lista donde se guardan las incidencias

    // Constructor vacío (es obligatorio)
    public Habitacion() {
    }

    // Constructor con parámetros (buena práctica, el id no se mete porque es autoincrement)
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Orientacion getOrientacion() {
        return orientacionHabitacion;
    }

    public void setOrientacion(Orientacion orientacionHabitacion) {
        this.orientacionHabitacion = orientacionHabitacion;
    }

    // toString para mostrar como texto
    @Override
    public String toString() {
        return "Habitacion [ID=" + id +
                ", Numero=" + numeroHabitacion +
                ", Tipo=" + tipo +
                ", Precio=" + precioPorNoche +
                ", Disponibilidad=" + disponibilidad +
                ", Orientación=" + orientacionHabitacion +
                "]";
    }
}
