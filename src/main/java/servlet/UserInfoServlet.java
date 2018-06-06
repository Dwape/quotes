package servlet;

import hibernate.HibernateFactory;
import hibernate.ManageUser;
import model.Comment;
import model.Post;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/secure/userInfo")
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = ManageUser.retrieveUser(request.getRemoteUser());
        List<Post> posts = new ArrayList<>();
        Transaction tx = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            user = session.get(User.class, user.getUsername());
            posts = new ArrayList<>(user.getPostArray());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }

        request.setAttribute("posts", posts);
        //System.out.println(posts.get(0).getQuote());

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/userInfoView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //doGet(request, response);
    }
}
