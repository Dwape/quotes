package servlet;

import hibernate.ManageUser;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/secure/manageAccount")
public class ManageAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ManageAccountServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/views/manageAccountView.jsp");

        User user = ManageUser.retrieveUser(request.getRemoteUser());
        HttpSession session = request.getSession();
        session.setAttribute("name", user.getName());
        session.setAttribute("surname", user.getSurname());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("dateOfBirth", user.getDateOfBirth());

        dispatcher.forward(request, response);
    }

    //Improve so that it makes sense (change the way in which the form being used is determined).
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("type").equals("1")) {
            updateInfo(request, response);
        }
        else {
            changePassword(request, response);
        }

        // should redirect to manage user

        doGet(request, response);
        //response.sendRedirect("/register");

        //display message saying changes were saved.
    }

    private void updateInfo(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        //Date is missing

        String username = request.getRemoteUser();
        ManageUser.changeName(username, name);
        ManageUser.changeSurname(username, surname);
        ManageUser.changeEmail(username, email);
        ManageUser.changeDateOfBirth(username,dateOfBirth);
        String message = "Changes saved";
        request.setAttribute("message", message);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {

        String oldPassword = request.getParameter("oldPassword");

        String username = request.getRemoteUser();

        User verify = ManageUser.verifyUser(username, oldPassword);
        if (verify == null){
            String errorMessage = "Incorrect password";
            request.setAttribute("errorMessage", errorMessage);
            return;
        }
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        if (!newPassword.equals(confirmNewPassword)) {
            String errorMessage = "Passwords do not match";
            request.setAttribute("errorMessage", errorMessage);
            return;
        }
        if (newPassword.equals(oldPassword)) {
            String errorMessage = "Your new password must be different from your current password";
            request.setAttribute("errorMessage", errorMessage);
            return;
        }
        ManageUser.changePassword(username, newPassword);
        String message = "Password changed successfully";
        request.setAttribute("message", message);
    }
}
