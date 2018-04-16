package hibernate;

import model.Book;
import model.Post;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageBook {

    /**
     * Adds a new book to the database.
     * @param newBook The book to be added to the database.
     * @return The book's id.
     */
    public static String addBook(Book newBook){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(newBook);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return newBook.getIdBook();
    }

    /**
     * Adds a post to a book.
     * @param book The book quoted.
     * @param post The post written by the user.
     */
    public static void addPost(Book book, Post post){
        //maybe we need to look for the user in the database here.
        book.getPostArray().add(post);
        updateBook(book);
    }

    /**
     * Updates a book in the database, changing any attributes that are different between user provided as parameter
     * and the book in the database.
     * @param book The modified book.
     */
    private static void updateBook(Book book){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
