package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hibernate.HibernateFactory;
import jackson.ResultSerializer;
import model.Post;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userPosts")
public class UserPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserPostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        List<Post> results = null;
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            User user = session.get(User.class, username);
            results = new ArrayList<>(user.getPostArray());
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Post.class, new ResultSerializer());
        mapper.registerModule(module);

        String loggedUser = request.getRemoteUser();
        if (loggedUser != null){
            for (Post post : results){
                post.setLoggedUsername(loggedUser);
            }
        }
        String postJson = mapper.writeValueAsString(results);
        PrintWriter out = response.getWriter();
        out.print(postJson);
        out.flush();
        if (loggedUser != null){
            for (Post post : results){
                post.setLoggedUsername(null);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
