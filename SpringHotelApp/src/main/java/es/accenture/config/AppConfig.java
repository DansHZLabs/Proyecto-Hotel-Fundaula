package es.accenture.config;  // IA: copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // Anotación para la configuración de Spring
@ComponentScan(basePackages = "es.accenture") // Escanea los paquetes y busca los beans
@PropertySource("classpath:application.properties") // Carga el application.properties
public class AppConfig {

}