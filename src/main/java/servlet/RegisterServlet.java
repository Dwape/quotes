package servlet;

import hibernate.ManageUser;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRemoteUser() != null) {
            response.sendRedirect("/secure/userInfo");
            return;
        }

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/registerView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        if (ManageUser.usernameInUse(username)) {
            String errorMessage = "Username already in use";
            request.setAttribute("errorMessage", errorMessage);
            doGet(request, response);
            return;
        }
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            String errorMessage = "Passwords do not match";
            request.setAttribute("errorMessage", errorMessage);
            doGet(request, response);
            return;
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        //Date dateOfBirth = request.getParameter("dateOfBirth"); //how to get a Date from the parameter returned by html
        //Date is missing

        User newUser = new User(username, email, password, name, surname, null); //date is missing.

        ManageUser.addUser(newUser);

        response.sendRedirect("/login");
    }
}
