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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@WebServlet("/postDetailsVote")
public class VoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VoteServlet() {
        super();
    }

    //doesn't work
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get all the comments and posts upvoted by the user.
        User user = ManageUser.retrieveUser(request.getRemoteUser());
        //we can get all the posts and comments of the user
        //we could do a query in the database or work directly with the arrays.
        ObjectMapper mapper = new ObjectMapper();
        String votesJson = mapper.writeValueAsString(user.getVoteArray());
        PrintWriter out = response.getWriter();
        out.print(votesJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameters = request.getParameterMap();

        boolean isPositive = Boolean.parseBoolean(parameters.get("isPositive")[0]);

        User user = ManageUser.retrieveUser(request.getRemoteUser());
        Post post = null;
        Comment comment = null;
        //Maybe only independent comments should have the post id
        long idPost = Long.parseLong(parameters.get("idPost")[0]);
        long idComment = Long.parseLong(parameters.get("idComment")[0]);
        if (idComment == -1) { //check if this is the correct way to compare.
            post = ManagePost.retrievePost(idPost);
        } else {
            comment = ManageComment.retrieveComment(idComment);
        }

        Vote vote = new Vote(post,comment,user,isPositive);

        ManageVote.addVote(vote);
    }
}
