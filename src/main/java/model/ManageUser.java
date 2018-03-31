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
     * @param newUser The user to be added to the database.
     * @return The user's id.
     */
    public Long addUser(User newUser){

        Transaction tx = null;
        Long userID = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            userID = (Long) session.save(newUser);
            newUser.setId(userID); //sets the user's id to the generated one.
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return userID;
    }

    /**
     * Updates a user's password.
     * @param userID The user's id.
     * @param newPassword The user's new password.
     */
    public void changePassword(Long userID, String newPassword){
        User user = retrieveUser(userID);
        user.setPassword(newPassword);
        updateUser(user);
    }

    /**
     * Updates a user's email address.
     * @param userID The user's id.
     * @param newEmail The new email address.
     */
    public void changeEmail(Long userID, String newEmail){
        User user = retrieveUser(userID);
        user.setEmail(newEmail);
        updateUser(user);
    }

    /**
     * Updates a user's name.
     * @param userID The user's id.
     * @param newName The new name.
     */
    public void changeName(Long userID, String newName){
        User user = retrieveUser(userID);
        user.setName(newName);
        updateUser(user);
    }

    /**
     * Updates a user's surname.
     * @param userID The user's id.
     * @param newSurname The new surname.
     */
    public void changeSurname(Long userID, String newSurname){
        User user = retrieveUser(userID);
        user.setSurname(newSurname);
        updateUser(user);
    }

    /**
     * Updates a user's date of birth.
     * @param userID The user's id.
     * @param newDateOfBirth The new date of birth.
     */
    public void changeDateOfBirth(Long userID, Date newDateOfBirth){
        User user = retrieveUser(userID);
        user.setDateOfBirth(newDateOfBirth);
        updateUser(user);
    }

    /**
     * Retrieves a user from the database, by using its key (username).
     * @param userID The user's id, used as key in the database.
     * @return The user.
     */
    private User retrieveUser(Long userID){

        Transaction tx = null;
        User user = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            user = session.get(User.class, userID);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Updates a user in the database, changing any attributes that are different between user provided as parameter
     * and the user in the database.
     * @param user The modified user.
     */
    private void updateUser(User user){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the database.
     * @param userID The user's id, used as the key in the database.
     */
    public void deleteUser(Long userID) {

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, userID);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
