package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.ManagePost;
import model.Comment;
import model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet("/getPost")
public class PostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String idPost = request.getParameter("id");
        long id = Long.parseLong(idPost);
        Post post = ManagePost.retrievePost(id);
        String loggedUser = request.getRemoteUser();
        post.setLoggedUsername(request.getRemoteUser()); //check if it works
        Set<Comment> comments = post.getCommentArray();
        if (loggedUser != null){
            for (Comment comment : comments){
                comment.setLoggedUsername(loggedUser);
            }
        }
        String postJson = mapper.writeValueAsString(post);
        PrintWriter out = response.getWriter();
        out.print(postJson);
        out.flush();
        post.setLoggedUsername(null); //if this is done with comments here, we should set username to null
        if (loggedUser != null){
            for (Comment comment : comments){
                comment.setLoggedUsername(null);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
