package servlet;


import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.ManagePost;
import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CommentsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        String idPost = request.getParameter("id");
        long id = Long.parseLong(idPost); //is it getParameter?
        Post post = ManagePost.retrievePost(id);
        String commentsJson = mapper.writeValueAsString(post.getCommentArray());
        PrintWriter out = response.getWriter();
        out.print(commentsJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
