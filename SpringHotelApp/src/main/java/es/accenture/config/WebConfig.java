package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //Anotación de configuración de Spring, la lee al arrancar y define los beans
@EnableWebMvc //Anotación que activa los controladores
@ComponentScan(basePackages = "es.accenture.controller") //Anotación que escanea el paquete y busca los beans
public class WebConfig implements WebMvcConfigurer {//esto le dice a Spring que se va a configurar comportamiento MVC

    @Bean
    public ViewResolver viewResolver() { //Convierte lo que le devuelve el controller en una jsp
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/vistas/");
        vr.setSuffix(".jsp");
        return vr;
    }
    
    
    @Override //Anotación que dice que se sobre escribe el método de WebMvcConfigurer
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("forward:/login.jsp"); //Esto hace que al entrar en localhost se abra login

    }
}