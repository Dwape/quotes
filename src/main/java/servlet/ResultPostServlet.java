package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hibernate.HibernateFactory;
import hibernate.ManagePost;
import jackson.ResultSerializer;
import model.Post;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import sun.security.provider.DSAKeyPairGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/resultPosts")
public class ResultPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ResultPostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String searchTerm = request.getParameter("q");
        List<Post> results = ManagePost.searchPosts(searchTerm);

        ObjectMapper mapper = new ObjectMapper();

        //Hibernate.initialize(results);

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
