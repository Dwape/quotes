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

        long voteId = ManageVote.hasUserVotedPost(id,user.getUsername());

        String outPrint = voteId == -1 ? "true" : "false";

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

        long voteId = ManageVote.hasUserVotedPost(idPost,user.getUsername());

        Vote vote = new Vote(post,null,user,isPositive);

        String outPrint;

        if (voteId == -1){
            ManageVote.addVoteToPost(vote);
            outPrint = "false";
        }else {
            ManageVote.deleteVoteFromPost(voteId);
            outPrint = "true";
        }

        PrintWriter out = response.getWriter();
        out.print(outPrint);
        out.flush();
    }
}
