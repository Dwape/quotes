package hibernate;
//java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/hemrajdb --dbname.0 testdb
import model.Book;
import model.Comment;
import model.Post;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.ArrayList;
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
        ManageUser.addPost(newPost.getUser(), newPost);
        ManageBook.addPost(newPost.getBook(), newPost);
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

            //could break everything
            ManageUser.removePost(post.getUser(), post);
            ManageBook.removePost(post.getBook(), post);

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

    /**
     * Searches for posts in the database using Lucene.
     * @param searchTerm The search term to be used in the search.
     * @return A list with the posts that match the searchTerm.
     */
    public static List<Post> searchPosts(String searchTerm){
        Transaction tx = null;
        List<Post> result = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            tx = fullTextSession.beginTransaction();
            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Post.class).get();

            org.apache.lucene.search.Query query = qb
                    .keyword()
                    .fuzzy()
                    .withPrefixLength( 0 )
                    .onField("quote")
                    .matching("\"" + searchTerm + "\"" + "*")
                    .createQuery();

            org.apache.lucene.search.Query query4 = qb
                    .bool()
                    .should(query)
                    .createQuery();

            Query hibQuery2 = fullTextSession.createFullTextQuery(query4, Post.class);
            result = hibQuery2.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public static void addComment(Post post, Comment comment){
        //maybe we need to look for the user in the database here.
        post.getCommentArray().add(comment);
        updatePost(post);
    }
}

