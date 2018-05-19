package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hibernate.ManageComment;
import hibernate.ManagePost;
import hibernate.ManageUser;
import hibernate.ManageVote;
import model.Comment;
import model.Post;
import model.User;
import model.Vote;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

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
        request.setAttribute("score", post.getScore());

        String username = request.getRemoteUser();
        if (username!=null){
            User user = ManageUser.retrieveUser(username);
            Vote vote = new Vote(post, null, user, true); //try this out
            Vote existing = ManageVote.hasUserVoted(vote);
            if (existing.getId() != -1){
                if (existing.isPositive()){
                    request.setAttribute("vote", "upvote");
                } else {
                    request.setAttribute("vote", "downvote");
                }
            } else {
                request.setAttribute("vote", "none");
            }
        }

        request.setAttribute("id", id);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/postDetailsView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameters = request.getParameterMap();
        String replyText = parameters.get("replyText")[0];

        User user = ManageUser.retrieveUser(request.getRemoteUser());
        Date datePosted = new Date();
        long idPost = Long.parseLong(parameters.get("idPost")[0]);
        Post post = ManagePost.retrievePost(idPost);

        Comment parent = null;
        if (parameters.size() == 3){ //this means that the comment has a parent, as the parent id is passed as a value.
            String idParentString = parameters.get("idParent")[0];
            long idParent = Long.parseLong(idParentString);
            parent = ManageComment.retrieveComment(idParent);
        }

        Comment comment = new Comment(user, post, parent, datePosted, replyText);

        ManageComment.addComment(comment);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();

        String commentJson = mapper.writeValueAsString(comment);
        PrintWriter out = response.getWriter();
        out.print(commentJson);
        out.flush();
    }
}
