package es.accenture.config; //copiada de otro proyecto y consultado si estaba bien con chat gpt

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Clase de configuracion encargada de la conexion con la base de
 * datos y la integracion de Hibernate con Spring.
 * 
 * Esta clase configura el origen de datos, la factory de sesiones y el gestor 
 * de transacciones.
 * 
 * @author danih y javi
 * @version 1.0
 */
@Configuration //Anotación de configuración de Spring, la lee al arrancar y define los beans
@EnableTransactionManagement //Anotación que activa las transaciones
@PropertySource("classpath:application.properties") // Anotación para cargar el properties
public class HibernateConfig {

	/*
     * Atributo que permite acceder a las propiedades en el archivo
     * application.properties para configurar la conexion y el Hibernate.
     */
    //Intyeccion por constructor
    private final Environment env;
    
    /**
     * Constructor por parametros en el que se realiza la inyeccion de dependencias
     * 
     * @param env objeto Environment
     */
    @Autowired 
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    /**
     * Metodo que crea y configura el bean encargado de establecer la conexion con bbdd
     * 
     * @return objeto DataSource configurado con los datos de conexion con bbdd
     */
    @Bean // Bean para crear la conexión con BBDD y mapea las entidades
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("db.driver"));
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    /**
     * Metodo que crea y configura la factory de sesiones y la comunicacion con la bbdd
     * 
     * @return objeto LocalSessionFactoryBean configurado para Hibernate
     */
    @Bean // Conecta con BBDD
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("es.accenture.entity");

        Properties props = new Properties();
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        factory.setHibernateProperties(props);

        return factory;
    }

    /**
     * Metodo que crea el gestor de transacciones de Hibernate.
     * 
     * @param sessionFactory de sesiones utilizada por Hibernate
     * @return objeto HibernateTransactionManager configurado
     */
    @Bean // Hace las transacciones
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory);
        return tx;
    }
}