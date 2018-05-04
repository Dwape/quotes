package servlet;

import hibernate.ManageComment;
import hibernate.ManagePost;
import hibernate.ManageUser;
import model.Comment;
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
import java.util.Date;
import java.util.List;

@WebServlet("/postDetails")
public class PostDetailsServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public PostDetailsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);

        request.setAttribute("comments", post.getCommentArray());

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

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        String commentText = request.getParameter("text");
        Date datePosted = new Date();

        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);

        Comment comment = new Comment(user, post, null, datePosted, commentText);

        //we need to add the parent comment if it had one

        ManageComment.addComment(comment);

        response.sendRedirect("/postDetails?id=" + id);
    }
}
