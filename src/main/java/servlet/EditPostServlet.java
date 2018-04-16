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

@WebServlet("/editPost")
public class EditPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditPostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //may need to verify if the user editing is the author of the post.
        long id = Long.parseLong(request.getParameter("id"));
        Post post = ManagePost.retrievePost(id);
        request.setAttribute("quote", post.getQuote());
        request.setAttribute("text", post.getDescription());
        request.setAttribute("bookTitle", post.getBook().getTitle());
        request.setAttribute("bookAuthor", post.getBook().getAuthor());

        request.setAttribute("id", id);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/editPostView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String newText = request.getParameter("text");
        //could probably be improved if we could get id directly.
        Long id = Long.parseLong(request.getParameter("postId"));
        ManagePost.changeText(id, newText);

        response.sendRedirect("/home");
    }
}
