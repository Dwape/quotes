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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

@WebServlet("/postDetailsVote")//??
public class VoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public VoteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPost = request.getParameter("id");
        long id = Long.parseLong(idPost);

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        Vote vote = ManageVote.hasUserVotedPost(id,user.getUsername());

        String outPrint = vote.getId() == -1 ? "true" : ("false " + String.valueOf(vote.isPositive()));

        PrintWriter out = response.getWriter();
        out.print(outPrint);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameters = request.getParameterMap();

        boolean isPositive = Boolean.parseBoolean(parameters.get("isPositive")[0]);

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        //Maybe only independent comments should have the post id
        long idPost = Long.parseLong(parameters.get("idPost")[0]);
        Post post = ManagePost.retrievePost(idPost);

        Vote voteMatched = ManageVote.hasUserVotedPost(idPost,user.getUsername());

        Vote vote = new Vote(post,null,user,isPositive);

        String outPrint;

        if (voteMatched.getId() == -1){
            ManageVote.addVoteToPost(vote);
            outPrint = "false";
        }else {
            if (voteMatched.isPositive() != vote.isPositive()){
                ManageVote.addVoteToPost(vote);
                outPrint = "false";
            }else{
                outPrint = "true";
            }
            ManageVote.deleteVoteFromPost(voteMatched.getId());
        }

        PrintWriter out = response.getWriter();
        out.print(outPrint);
        out.flush();
    }
}
