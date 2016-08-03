/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author server
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final Configuration configuration;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
             configuration = new Configuration().configure(); 
             
             StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(). applySettings(configuration.getProperties()); 
             SessionFactory factory = configuration.buildSessionFactory(builder.build());
             sessionFactory = configuration.buildSessionFactory(builder.build());
             //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        
        //Recogiendo Datos de la sesion para saber que usuario ingreso la maestria promocion
//        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        if(usuario != null){
//            configuration.setProperty("hibernate.connection.username", ""+usuario.getNick().toLowerCase()+"");
//            configuration.setProperty("hibernate.connection.password", ""+usuario.getClave()+"");
//        }else{
//            configuration.setProperty("hibernate.connection.username", "postgres");
//            configuration.setProperty("hibernate.connection.password", "123456");
//        }
        
        return sessionFactory;
    }
}
