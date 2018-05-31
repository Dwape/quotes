package hibernate;

import model.Comment;
import model.Vote;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageComment {

    public static Long addComment(Comment newComment){

        Transaction tx = null;
        Long commentID = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            commentID = (Long) session.save(newComment);
            newComment.setId(commentID); //sets the comment's id to the generated one.
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        //The comment may be added automatically to the sets of all objects that have a one to many relationship with it.

        /*
        if (newComment.getParent() == null) {
            ManagePost.addComment(newComment.getPost(), newComment);
        } else {
            ManageComment.addChild(newComment.getParent(), newComment);
        }

        ManageUser.addComment(newComment.getUser(), newComment);
        */
        return commentID;
    }

    private static void updateComment(Comment comment){

        Transaction tx = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(comment);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public static Comment retrieveComment(long id){

        Transaction tx = null;
        Comment comment = null;

        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            comment = session.get(Comment.class, id);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return comment;
    }

    public static void addChild(Comment parent, Comment child){
        //maybe we need to look for the user in the database here.
        parent.getCommentArray().add(child);
        updateComment(parent);
    }
}
