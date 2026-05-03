package es.accenture.entity;             //Esta ya está terminada, falta poner la relación con incidencia
										 //habrá que poner @OneToMany(mappedBy="habitacion" //Relación 1:N con incidencia encima de private List<Incidencia>incidencias; y sus imports y luego comprobar y en incidencia meter la suya que manda sobre ella, no poner cascade por las reglas sde negocio

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

@Entity //Anotación para representar una tabla en la BBDD
@Table(name="habitaciones") //Anotación que indica cómo se llama la tabla que representa
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

    @Id //clave primaria INT
    @GeneratedValue(strategy=GenerationType.IDENTITY)//Anotación para generar automáticamente el ID en la base de datos (AUTO_INCREMENT)
    @Column(name="id_habitacion",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido aunque al ser PK nullable=false no haría falta
    private int idHabitacion;

    @Column(name="numero_habitacion",nullable=false,unique=true) //Anotación que indica cómo se llama la tabla que representa y que es requeridoINT
    private String numeroHabitacion;

    @Enumerated(EnumType.STRING) //Anotación para los enum, le dice a bbdd que son strings
    @Column(name="tipo_habitacion",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido ENUM
    private Tipo tipo;

    @Column(name="precio_noche",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requerido DECIMAL(7,2)
    private BigDecimal precioPorNoche;

    @Enumerated(EnumType.STRING) //Anotación para los enum, le dice a bbdd que son strings
    @Column(name="disponibilidad_habitacion",nullable=false) //Anotación que indica cómo se llama la tabla que representa y que es requeridoENUM
    private Disponibilidad disponibilidad;

    @Enumerated(EnumType.STRING) //Anotación para los enum, le dice a bbdd que son strings
    @Column(name="orientacion_habitacion")//Anotación que indica cómo se llama la tabla que representa ENUM
    private Orientacion orientacionHabitacion;
    
    @OneToMany(mappedBy="habitacion",fetch=FetchType.LAZY)//Anotación para la relación 1N con habitación 1 habitación puede tener muchas incidencias y Lazy porque se dan datos bajo demanda
    													      //sin Cascade para propagar el CRUD de lo que se haga en habitación a incidencias porque nos rompe la lógica de negocio    
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
    public int getIdHabitacion() {
        return idHabitacion;
    }

    /*public void setIdHabitacion(int idHabitacion) { //el setter se quita porque es autoincremental
        this.idHabitacion = idHabitacion;
    }*/

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
