package hibernate;

import model.Comment;
import model.Post;
import model.User;
import model.Vote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;

public class ManageVote {

    public ManageVote() {
    }

    /**
     * Adds a new vote to the database.
     * @param newVote The vote to be added to the database.
     * @return The vote's id.
     */
    //this method should check the logic when adding votes
    public static Long addVote(Vote newVote){

        Long voteID = null;

        Vote previousVote = hasUserVoted(newVote);

        Transaction tx = null;

        if ((previousVote.getId() == -1) || (previousVote.isPositive() != newVote.isPositive())) {
            try (Session session = HibernateFactory.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                voteID = (Long) session.save(newVote);
                newVote.setId(voteID); //sets the vote's id to the generated one.
                if (newVote.getComment() == null){
                    Post post = newVote.getPost();
                    post.addVote(newVote.isPositive());
                    session.update(post);
                } else {
                    Comment comment = newVote.getComment();
                    comment.addVote(newVote.isPositive());
                    session.update(comment);
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            }
        }
        if (previousVote.getId() != -1) {
            deleteVote(previousVote.getId());
        }
        return voteID;
    }

    /**
     * Deletes a vote from the database.
     * This method must not be called for a vote that does not exist in the database.
     * @param voteID The user's id, used as the key in the database.
     */
    public static void deleteVote(Long voteID) {

        Transaction tx = null;
        Vote vote;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            vote = session.get(Vote.class, voteID);

            session.delete(vote);

            if (vote.getComment() == null) {
                //ManagePost.removeVote(vote.getPost(), vote);
                Post post = vote.getPost();
                post.removeVote(vote.isPositive());
            } else {
                //ManageComment.removeVote(vote.getComment(), vote);
                Comment comment = vote.getComment();
                comment.removeVote(vote.isPositive());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    //change the method's name
    public static Vote hasUserVoted(Vote newVote){
        Transaction tx = null;
        long voteID = -1;
        Boolean isPositive = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Query query;
            if (newVote.getComment() == null){
                //change so that sql can not be injected.
                query = session.createQuery("SELECT V.id,V.isPositive FROM Vote V WHERE idPost= " + newVote.getPost().getId() + " AND username = '" + newVote.getUser().getUsername() +"'");
            } else {
                query = session.createQuery("SELECT V.id,V.isPositive FROM Vote V WHERE idComment= " + newVote.getComment().getId() + " AND username = '" + newVote.getUser().getUsername() +"'");
            }
            Iterator results = query.list().iterator();

            if (results.hasNext()){
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
