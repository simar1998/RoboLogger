package com.vyo.robologger.main.db;




import com.vyo.robologger.main.Main;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;

/**
 * The type Hibernate thread.
 */
public class DatabaseInit {

    public static Configuration configuration;

    private static final String DRIVER_STRING =  "com.mysql.cj.jdbc.Driver";

    public static ArrayList<Class> managedEntities = new ArrayList<>();


    //Classes to add to the annoted list for the database
    static {
        managedEntities.add(ImageData.class);
    }

    /**
     * The constant ourSessionFactory.
     */
    public static SessionFactory ourSessionFactory;


    /**
     * Gets session.
     *
     * @return the session
     * @throws HibernateException the hibernate exception
     */
    public static SessionFactory getSessionFactory() throws HibernateException {
        return ourSessionFactory;
    }


    /**
     * Build session factory session factory.ii
     *
     * @return the session factory
     */
    public static void buildSessionFactory() {
        try {
            System.out.println("Session factory built, hibernate thread");
            configuration = new Configuration()
//                    .setProperty("hibernate.connection.url", System.getenv("MYSQL_URL"))
                    .setProperty("hibernate.connection.url", Main.bootConfig.getMYSQL_URL())
                    .setProperty("hibernate.connection.driver_class", DRIVER_STRING)
                    .setProperty("hibernate.connection.username", Main.bootConfig.getMYSQL_USR())
                    .setProperty("hibernate.connection.password", Main.bootConfig.getMYSQL_PASSWD())
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.c3p0.acquire_increment","1")
                    .setProperty("hibernate.c3p0.idle_test_period","100")
                    .setProperty("hibernate.c3p0.max_size","100")
                    .setProperty("hibernate.c3p0.max_statements","100")
                    .setProperty("hibernate.c3p0.min_size","10")
                    .setProperty("hibernate.c3p0.timeout","180")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update");

            for (Class entityClass : managedEntities) {
                configuration.addAnnotatedClass(entityClass);
            }
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("DB MANAGEMENT THREAD IS OPERATIONAL !!");
        }
        catch (Exception e){
            System.out.println("HIBERNATE SESSION FACTORY HAS THROWN AN ERROR AND THUS IS NOT ACTIVE !!!");
        }
    }

}