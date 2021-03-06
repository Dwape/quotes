package servlet;

import hibernate.ManageBook;
import hibernate.ManagePost;
import hibernate.ManageUser;
import model.Book;
import model.Post;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/writePost")
public class WritePostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public WritePostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/writePostView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = ManageUser.retrieveUser(request.getRemoteUser());

        String quote = request.getParameter("quote");
        quote = quote.replaceAll("^\"|\"$", "");
        String postText = request.getParameter("text");
        String bookId = request.getParameter("bookId");
        String bookTitle = request.getParameter("bookTitle");
        String bookAuthor = request.getParameter("bookAuthor");
        String bookPublisher = request.getParameter("bookPublisher");
        String bookPlacePublished = request.getParameter("bookPlacePublished");
        String bookPublishedDate = request.getParameter("bookPublishedDate");
        String postPage = request.getParameter("postPage");
        Date datePosted = new Date();
        //String book = request.getParameter("book");
        //Book id should be provided by Google books api.
        Book book = new Book(bookId, bookTitle, bookAuthor);
        Post post = new Post(quote, datePosted, postText, book, user);
        post.setPage(postPage);
        post.setPublisher(bookPublisher);
        post.setDatePublished(bookPublishedDate);
        post.setPlacePublished(bookPlacePublished);

        try{
            ManageBook.addBook(book); //adds the book to the database.
        } catch(Exception e) {
            //if the book can not be added, it is already in the database.
            //we could check if the book is already in the database instead.
        }

        ManagePost.addPost(post);

        response.sendRedirect("/home");
    }
}
