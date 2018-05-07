package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        ObjectMapper mapper = new ObjectMapper();

        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);

        request.setAttribute("comments", post.getCommentArray());

        request.setAttribute("quote", post.getQuote());
        request.setAttribute("text", post.getDescription());
        request.setAttribute("bookTitle", post.getBook().getTitle());
        request.setAttribute("bookAuthor", post.getBook().getAuthor());
        request.setAttribute("postedBy", post.getUser().getUsername());
        request.setAttribute("datePosted", post.getDatePosted());

        //String commentsJson = new Gson().toJson(post.getCommentArray());
        //request.setAttribute("commentsJson", commentsJson);

        String commentsJson = mapper.writeValueAsString(post.getCommentArray());
        request.setAttribute("commentsJson", commentsJson);

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

        //Maybe only independent comments should have the post id
        long idPost = Long.parseLong(request.getParameter("idPost"));
        Post post = ManagePost.retrievePost(idPost);

        //we need to add the parent comment if it had one
        //request.getParameter("idParent")
        //it should be null if it is a reply to the post directly.

        Comment parent = null;
        String idParentString = request.getParameter("idParent");
        if (idParentString != null){
            long idParent = Long.parseLong(idParentString);
            parent = ManageComment.retrieveComment(idParent);
        }

        //A query could be done where we take into account which post the parent comment belongs to, so as not to
        //look for the comment among all other comments

        Comment comment = new Comment(user, post, parent, datePosted, commentText);

        ManageComment.addComment(comment);

        response.sendRedirect("/postDetails?id=" + idPost);
    }
}
