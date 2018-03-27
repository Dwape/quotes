package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private static SessionFactory sessionFactory = null;

    public static synchronized SessionFactory getSessionFactory() {
        if ( sessionFactory == null ) {

            // exception handling omitted for brevity

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            sessionFactory = new Configuration().buildSessionFactory( serviceRegistry );
        }
        return sessionFactory;
    }
}
