package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

/**
 * Created by Dwape on 3/27/18.
 */
public class ManageUser {

    public ManageUser(){}

    /**
     * Adds a new user to the database.
     * @param username Used to identify the user, it is the key in the database.
     * @param email The user's email.
     * @param password The user's password, used to log-in.
     * @param name The user's real name.
     * @param surname The user's surname.
     * @param dateOfBirth The user's date of birth.
     * @return The user's username.
     */
    public String addUser(String username, String email, String password, String name, String surname, Date dateOfBirth){

        User registree = new User(username, email, password, name, surname, dateOfBirth);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(registree);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return username; //is this returned by save method? It shouldn't be necessary unless we use a generated id.
    }
}
