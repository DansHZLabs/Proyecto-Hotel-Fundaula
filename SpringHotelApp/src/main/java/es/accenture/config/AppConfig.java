package es.accenture.config;  //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase de configuracion principal de Spring encargada de definir las
 * anotaciones necesarias para las cargas de los componentes.
 * 
 * Esta clase permite a Spring detectar automaticamente los beans definidos
 * dentro del paquete que sea y cargar el archivo de propiedades.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Configuration // Anotación para la configuración de Spring
@ComponentScan(basePackages = "es.accenture") // Escanea los paquetes y busca los beans
@PropertySource("classpath:application.properties") // Carga el application.properties
public class AppConfig {

}