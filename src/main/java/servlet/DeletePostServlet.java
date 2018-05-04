package servlet;

import hibernate.ManageBook;
import hibernate.ManagePost;
import hibernate.ManageUser;
import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeletePostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);
        ManagePost.deletePost(id);

        response.sendRedirect("/home");
    }
}
