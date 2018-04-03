package hibernate;

import hibernate.HibernateFactory;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

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
    public static Long addUser(User newUser){

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
    public static void changePassword(Long userID, String newPassword){
        User user = retrieveUser(userID);
        user.setPassword(newPassword);
        updateUser(user);
    }

    /**
     * Updates a user's email address.
     * @param userID The user's id.
     * @param newEmail The new email address.
     */
    public static void changeEmail(Long userID, String newEmail){
        User user = retrieveUser(userID);
        user.setEmail(newEmail);
        updateUser(user);
    }

    /**
     * Updates a user's name.
     * @param userID The user's id.
     * @param newName The new name.
     */
    public static void changeName(Long userID, String newName){
        User user = retrieveUser(userID);
        user.setName(newName);
        updateUser(user);
    }

    /**
     * Updates a user's surname.
     * @param userID The user's id.
     * @param newSurname The new surname.
     */
    public static void changeSurname(Long userID, String newSurname){
        User user = retrieveUser(userID);
        user.setSurname(newSurname);
        updateUser(user);
    }

    /**
     * Updates a user's date of birth.
     * @param userID The user's id.
     * @param newDateOfBirth The new date of birth.
     */
    public static void changeDateOfBirth(Long userID, Date newDateOfBirth){
        User user = retrieveUser(userID);
        user.setDateOfBirth(newDateOfBirth);
        updateUser(user);
    }

    /**
     * Retrieves a user from the database, by using its key (user id).
     * @param userID The user's id, used as key in the database.
     * @return The user.
     */
    private static User retrieveUser(Long userID){

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
    private static void updateUser(User user){

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
    public static void deleteUser(Long userID) {

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

    /**
     * Verifies a user, checking if his username exists in the database and if the password provided is the one
     * that corresponds to said username.
     * @param username The user's username, used to log in.
     * @param password The user's password, used to log in.
     * @return The user, if the password is incorrect or the user does not exist in the database, this method
     * returns null.
     */
    public static User verifyUser(String username, String password) {

        Transaction tx = null;
        Session session = HibernateFactory.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            List results = query.list();
            if (results.size() == 0) return null;
            User user = (User) results.get(0);
            if (user.getPassword().equals(password)) return user;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if the username passed as parameter is already in use.
     * @param username The username that will be searched.
     * @return True if the username is already in use, false otherwise.
     */
    public static boolean usernameInUse(String username) {
        Transaction tx = null;
        Session session = HibernateFactory.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            String hql = "FROM User U WHERE U.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            List results = query.list();
            if (results.size() != 0) return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
