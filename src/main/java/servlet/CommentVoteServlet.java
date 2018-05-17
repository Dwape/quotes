package servlet;

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
        import java.util.Map;

@WebServlet("/commentVote")//??
public class CommentVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CommentVoteServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*String idPost = request.getParameter("id");
        long id = Long.parseLong(idPost);

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        Vote vote = ManageVote.hasUserVotedPost(id,user.getUsername());

        String outPrint = vote.getId() == -1 ? "true" : ("false " + String.valueOf(vote.isPositive()));

        PrintWriter out = response.getWriter();
        out.print(outPrint);
        out.flush();*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String[]> parameters = request.getParameterMap();

        boolean isPositive = Boolean.parseBoolean(parameters.get("isPositive")[0]);

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        long idComment = Long.parseLong(parameters.get("idComment")[0]);
        Comment comment = ManageComment.retrieveComment(idComment);

        Vote voteMatched = ManageVote.hasUserVotedComment(idComment,user.getUsername());

        Vote vote = new Vote(null,comment,user,isPositive);

        String outPrint;

        if (voteMatched.getId() == -1){
            ManageVote.addVoteToComment(vote);
            outPrint = "false";
        }else {
            if (voteMatched.isPositive() != vote.isPositive()){
                ManageVote.addVoteToComment(vote);
                outPrint = "false";
            }else{
                outPrint = "true";
            }
            ManageVote.deleteVoteFromComment(voteMatched.getId());
        }

        PrintWriter out = response.getWriter();
        out.print(outPrint);
        out.flush();
    }
}