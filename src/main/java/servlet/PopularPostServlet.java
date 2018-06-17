package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hibernate.ManagePost;
import jackson.PostSerializer;
import jackson.ResultSerializer;
import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/popularPost")
public class PopularPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PopularPostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Post> topPosts = ManagePost.getMostPopular();
        /*request.setAttribute("topPosts", topPosts);*/

        ObjectMapper mapper = new ObjectMapper();

        //Hibernate.initialize(results);

        SimpleModule module = new SimpleModule();
        module.addSerializer(Post.class, new ResultSerializer());
        mapper.registerModule(module);

        String postJson = mapper.writeValueAsString(topPosts);
        PrintWriter out = response.getWriter();
        out.print(postJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
