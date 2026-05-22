package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Clase de configuracion encargada de definir el comportamiento MVC de
 * Spring y que se vean las vistas.
 * 
 * Esta clase configura el ViewResolver utilizado para localizar las vistas
 * jsp y define los controladores de vistas.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Configuration //Anotación de configuración de Spring, la lee al arrancar y define los beans
@EnableWebMvc //Anotación que activa los controladores
@ComponentScan(basePackages = "es.accenture.controller") //Anotación que escanea el paquete y busca los beans
public class WebConfig implements WebMvcConfigurer {//esto le dice a Spring que se va a configurar comportamiento MVC

	/**
	 * Metodo encargado de crear y configurar el resolver de vistas utilizado
	 * por Spring MVC para localizar las paginas jsp.
	 * 
	 * @return objeto ViewResolver configurado con la ruta y extension de las vistas
	 */
    @Bean
    public ViewResolver viewResolver() { //Convierte lo que le devuelve el controller en una jsp
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/vistas/");
        vr.setSuffix(".jsp");
        return vr;
    }
    
    /**
     * Metodo que registra controladores de vistas.
     * 
     * @param registry objeto encargado de almacenar los controladores de vistas
     */
    @Override //Anotación que dice que se sobre escribe el método de WebMvcConfigurer
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("forward:/Login.jsp"); //Esto hace que al entrar en localhost se abra login

    }
}