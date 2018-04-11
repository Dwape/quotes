package servlet;

import hibernate.ManagePost;
import hibernate.ManageUser;
import model.Post;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/writePost")
public class WritePostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public WritePostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/writePostView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String quote = request.getParameter("quote");
        String postText = request.getParameter("text");
        Date datePosted = new Date();
        //String book = request.getParameter("book");
        //Book id should be provided by Google books api.
        Post post = new Post(quote, datePosted, postText, 1, request.getRemoteUser());
        ManagePost.addPost(post);

        response.sendRedirect("/home");
    }
}
