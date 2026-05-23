package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Clase encargada de inicializar la configuracion principal mediante anotaciones de Spring.
 * 
 * Esta clase sustituye al web.xml, definiendo las clases
 * de configuracion, la configuracion del DispatcherServlet y las rutas
 * 
 * @author danih y javi
 * @version 1.0
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * Metodo que devuelve las clases de configuracion
	 * encargadas de configurar los componentes la persistencia.
	 * 
	 * @return array de clases de configuracion principal
	 */
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return new Class[] { AppConfig.class, HibernateConfig.class };
    }

    /**
     * Metodo que devuelve las clases que se encargan de la configuracion del
     * DispatcherServlet y de las vistas.
     * 
     * @return array de clases de configuracion
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }

    /**
     * Metodo que define las rutas que seran gestionadas por el DispatcherServlet
     * 
     * @return array con los patrones de URL
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}