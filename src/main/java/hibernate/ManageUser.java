package hibernate;

import hibernate.HibernateFactory;
import model.Comment;
import model.Post;
import model.User;
import model.Vote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

/**
 * Created by Dwape on 3/27/18.
 */
public class ManageUser{

    public ManageUser(){}

    /**
     * Adds a new user to the database.
     * @param newUser The user to be added to the database.
     * @return The user's username.
     */
    public static String addUser(User newUser){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(newUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return newUser.getUsername();
    }

    /**
     * Updates a user's password.
     * @param username The user's username.
     * @param newPassword The user's new password.
     */
    public static void changePassword(String username, String newPassword){
        User user = retrieveUser(username);
        user.setPassword(newPassword);
        updateUser(user);
    }

    /**
     * Updates a user's email address.
     * @param username The user's username.
     * @param newEmail The new email address.
     */
    public static void changeEmail(String username, String newEmail){
        User user = retrieveUser(username);
        user.setEmail(newEmail);
        updateUser(user);
    }

    /**
     * Updates a user's name.
     * @param username The user's username.
     * @param newName The new name.
     */
    public static void changeName(String username, String newName){
        User user = retrieveUser(username);
        user.setName(newName);
        updateUser(user);
    }

    /**
     * Updates a user's surname.
     * @param username The user's username.
     * @param newSurname The new surname.
     */
    public static void changeSurname(String username, String newSurname){
        User user = retrieveUser(username);
        user.setSurname(newSurname);
        updateUser(user);
    }

    /**
     * Updates a user's date of birth.
     * @param username The user's username.
     * @param newDateOfBirth The new date of birth.
     */
    public static void changeDateOfBirth(String username, String newDateOfBirth){
        User user = retrieveUser(username);
        user.setDateOfBirth(newDateOfBirth);
        updateUser(user);
    }

    /**
     * Retrieves a user from the database, by using its key (username).
     * @param username The user's username, used as key in the database.
     * @return The user.
     */
    public static User retrieveUser(String username){

        Transaction tx = null;
        User user = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            user = session.get(User.class, username);
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
     * @param username The user's username, used as the key in the database.
     */
    public static void deleteUser(String username) {

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, username);
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
            User user = session.get(User.class, username);
            if (user == null) return null;
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
            User user = session.get(User.class, username);
            if (user != null) return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Adds a post to a user.
     * @param user The user that wrote the post.
     * @param post The post written by the user.
     */
    public static void addPost(User user, Post post){
        //maybe we need to look for the user in the database here.
        Transaction tx = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            user.getPostArray().add(post);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        updateUser(user);
    }

    /**
     * Removes a post from a user.
     * @param user The user that wrote the post.
     * @param post The post written by the user.
     */
    public static void removePost(User user, Post post){
        //maybe we need to look for the user in the database here.
        user.getPostArray().remove(post);
        updateUser(user);
    }

    public static void addComment(User user, Comment comment){
        //maybe we need to look for the user in the database here.
        user.getCommentArray().add(comment);
        updateUser(user);
    }

    public static void removeComment(User user, Comment comment){
        //maybe we need to look for the user in the database here.
        user.getCommentArray().remove(comment);
        updateUser(user);
    }
}
