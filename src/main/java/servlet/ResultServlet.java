package servlet;

import hibernate.ManagePost;
import model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ResultServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String searchTerm = request.getParameter("q");
        List<Post> results = ManagePost.searchPosts(searchTerm);
        request.setAttribute("posts", results);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/resultView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
