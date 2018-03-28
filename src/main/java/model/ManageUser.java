package model;

import hibernate.HibernateFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;

/**
 * Created by Dwape on 3/27/18.
 */
public class ManageUser {

    public ManageUser(){}

    /**
     * Adds a new user to the database.
     * @param username Used to identify the user, used as the key in the database.
     * @param email The user's email.
     * @param password The user's password, used to log-in.
     * @param name The user's real name.
     * @param surname The user's surname.
     * @param dateOfBirth The user's date of birth.
     * @return The user's username.
     */
    public String addUser(String username, String email, String password, String name, String surname, Date dateOfBirth){

        User newUser = new User(username, email, password, name, surname, dateOfBirth);

        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(newUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return username; //is this returned by save method? It shouldn't be necessary unless we use a generated id.
    }

    /**
     * Updates a user's password.
     * @param username The user's username.
     * @param newPassword The user's new password.
     */
    public void changePassword(String username, String newPassword){
        User user = retrieveUser(username);
        user.setPassword(newPassword);
        updateUser(user);
    }

    /**
     * Updates a user's email address.
     * @param username The user's username.
     * @param newEmail The new email address.
     */
    public void changeEmail(String username, String newEmail){
        User user = retrieveUser(username);
        user.setEmail(newEmail);
        updateUser(user);
    }

    /**
     * Updates a user's name.
     * @param username The user's username.
     * @param newName The new name.
     */
    public void changeName(String username, String newName){
        User user = retrieveUser(username);
        user.setName(newName);
        updateUser(user);
    }

    /**
     * Updates a user's surname.
     * @param username The user's username.
     * @param newSurname The new surname.
     */
    public void changeSurname(String username, String newSurname){
        User user = retrieveUser(username);
        user.setSurname(newSurname);
        updateUser(user);
    }

    /**
     * Updates a user's date of birth.
     * @param username The user's username.
     * @param newDateOfBirth The new date of birth.
     */
    public void changeDateOfBirth(String username, Date newDateOfBirth){
        User user = retrieveUser(username);
        user.setDateOfBirth(newDateOfBirth);
        updateUser(user);
    }

    /**
     * Retrieves a user from the database, by using its key (username).
     * @param username The user's username, used as key in the database.
     * @return The user.
     */
    private User retrieveUser(String username){

        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx = null;
        User user = null;

        try {
            tx = session.beginTransaction();
            user = session.get(User.class, username);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * Updates a user in the database, changing any attributes that are different between user provided as parameter
     * and the user in the database.
     * @param user The modified user.
     */
    private void updateUser(User user){

        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Deletes a user from the database.
     * @param username The user's username, used as the key in the database.
     */
    public void deleteUser(String username) {

        Session session = HibernateFactory.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = session.get(User.class, username);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
