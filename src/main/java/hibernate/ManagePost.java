package hibernate;
//java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/hemrajdb --dbname.0 testdb
import model.Post;
import org.apache.lucene.search.FuzzyQuery;
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

    /*
    public static List<Post> searchPosts(String searchTerm) {


        EntityManager em = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

// create native Lucene query unsing the query DSL
// alternatively you can write the Lucene query using the Lucene query parser
// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("title", "subtitle", "authors.name")
                .matching("Java rocks!")
                .createQuery();

// wrap Lucene query in a javax.persistence.Query
        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Book.class);

// execute search
        List result = jpaQuery.getResultList();

        em.getTransaction().commit();
        em.close();

    }
    */

    public static List<Post> searchPosts(String searchTerm) {
        Transaction tx = null;
        List<Post> result = new ArrayList<>();
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            tx = fullTextSession.beginTransaction();
            //fullTextSession.createIndexer().startAndWait(); //check if it is necessary.
            QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Post.class).get();

            /*
            org.apache.lucene.search.Query query = qb
                    .keyword()
                    .onFields("quote")
                    .matching(searchTerm)
                    .createQuery();
                    */
            org.apache.lucene.search.Query query = qb
                    .keyword()
                    .fuzzy()
                    .withPrefixLength( 0 )
                    .onField("quote")
                    .matching(searchTerm)
                    .createQuery();
            /*
            org.apache.lucene.search.Query query = qb
                    .bool()
                        .must(qb
                                .keyword()
                                .fuzzy()
                                .withPrefixLength( 0 )
                                .onField("quote")
                                .matching(searchTerm)
                                .createQuery())
                        .should(qb
                                .phrase()
                                .withSlop(4)
                                .onField("quote")
                                .sentence(searchTerm)
                                .createQuery())
                    .createQuery();
            */
            // wrap Lucene query in a org.hibernate.Query
            Query hibQuery = fullTextSession.createFullTextQuery(query, Post.class);
            result = hibQuery.list(); //check if cast works
            tx.commit();
            //session.close(); //may be necessary.
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }
}

