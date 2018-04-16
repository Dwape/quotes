package hibernate;
//java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/hemrajdb --dbname.0 testdb
import model.Post;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class ManagePost {

    public ManagePost() {
    }

    /**
     * Adds a new post to the database.
     * @param newPost The post to be added to the database.
     * @return The post's id.
     */
    public static Long addPost(Post newPost){

        Transaction tx = null;
        Long postID = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            postID = (Long) session.save(newPost);
            newPost.setId(postID); //sets the user's id to the generated one.
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return postID;
    }

    /**
     * Deletes a user from the database.
     * @param postID The user's id, used as the key in the database.
     */
    public static void deletePost(Long postID) {

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Post post = session.get(Post.class, postID);
            session.delete(post);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a post from the database, by using its key (id).
     * @param id The post's id, used as key in the database.
     * @return The post.
     */
    public static Post retrievePost(long id){

        Transaction tx = null;
        Post post = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            post = session.get(Post.class, id);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return post;
    }

    /**
     * Updates a post in the database, changing any attributes that are different between user provided as parameter
     * and the post in the database.
     * @param post The modified post.
     */
    private static void updatePost(Post post){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(post);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Updates a post's text.
     * @param id The post's id.
     * @param text The new text.
     */
    public static void changeText(Long id, String text){
        Post post = retrievePost(id);
        post.setDescription(text);
        updatePost(post);
    }
}
