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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/postDetails")
public class PostDetail extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public PostDetail() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);
        request.setAttribute("quote", post.getQuote());
        request.setAttribute("text", post.getDescription());
        request.setAttribute("bookTitle", post.getBook().getTitle());
        request.setAttribute("bookAuthor", post.getBook().getAuthor());
        request.setAttribute("postedBy", post.getUser().getUsername());
        request.setAttribute("datePosted", post.getDatePosted());

        request.setAttribute("id", id);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/postDetailsView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
