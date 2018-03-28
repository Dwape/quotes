package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static synchronized SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //private static SessionFactory sessionFactory = new Configuration().buildSessionFactory(new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build());
}
