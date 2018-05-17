package hibernate;

import model.Vote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManageVote {

    public ManageVote() {
    }

    /**
     * Adds a new vote to the database.
     * @param newVote The vote to be added to the database.
     * @return The vote's id.
     */
    public static Long addVoteToPost(Vote newVote){

        Transaction tx = null;
        Long voteID = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            voteID = (Long) session.save(newVote);
            newVote.setId(voteID); //sets the vote's id to the generated one.
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        ManagePost.addVote(newVote.getPost(),newVote);
        ManageUser.addVote(newVote.getUser(),newVote);
        return voteID;
    }

    /**
     * Adds a new vote to the database.
     * @param newVote The vote to be added to the database.
     * @return The vote's id.
     */
    public static Long addVoteToComment(Vote newVote){

        Transaction tx = null;
        Long voteID = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            voteID = (Long) session.save(newVote);
            newVote.setId(voteID); //sets the vote's id to the generated one.
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        ManageComment.addVote(newVote.getComment(),newVote);
        ManageUser.addVote(newVote.getUser(),newVote);
        return voteID;
    }

    /**
     * Deletes a user from the database.
     * @param voteID The user's id, used as the key in the database.
     */
    public static void deleteVoteFromPost(Long voteID) {

        Transaction tx = null;
        Vote vote = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            vote = session.get(Vote.class, voteID);

            //could break everything
            /*ManageUser.removeVote(vote.getUser(), vote);
            ManagePost.removeVote(vote.getPost(),vote);*/

            session.delete(vote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        ManageUser.removeVote(vote.getUser(), vote);
        ManagePost.removeVote(vote.getPost(),vote);
    }

    /**
     * Deletes a user from the database.
     * @param voteID The user's id, used as the key in the database.
     */
    public static void deleteVoteFromComment(Long voteID) {

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Vote vote = session.get(Vote.class, voteID);

            //could break everything
            ManageUser.removeVote(vote.getUser(), vote);
            ManageComment.removeVote(vote.getComment(),vote);

            session.delete(vote);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static Vote hasUserVotedPost(long postID, String username){
        Transaction tx = null;
        long voteID = -1;
        Boolean isPositive = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT V.id,V.isPositive FROM Vote V WHERE idPost= " + postID + " AND username = '" + username +"'");
            Iterator results = query.list().iterator();

            while ( results.hasNext() ) {
                Object[] tuple = (Object[]) results.next();
                voteID = (Long) tuple[0];
                isPositive = (Boolean) tuple[1];
            }
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        Vote vote = new Vote();
        vote.setId(voteID);
        if (isPositive!=null) vote.setPositive(isPositive);
        return vote;
    }
}
