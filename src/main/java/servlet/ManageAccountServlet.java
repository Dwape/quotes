package servlet;

import hibernate.ManageUser;
import model.User;
import securityFilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/manage_account")
public class ManageAccountServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ManageAccountServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/manageAccountView.jsp");

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

        doGet(request, response); //REMOVE

        //display message saying changes were saved.
    }

    private void updateInfo(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        //Date is missing

        User user = AppUtils.getLoginedUser(request.getSession());
        ManageUser.changeName(user.getId(), name);
        ManageUser.changeSurname(user.getId(), surname);
        ManageUser.changeEmail(user.getId(), email);
        String message = "Changes saved";
        request.setAttribute("message", message);

        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        AppUtils.storeLoginedUser(request.getSession(), user);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {

        String oldPassword = request.getParameter("oldPassword");
        User user = AppUtils.getLoginedUser(request.getSession());
        User verify = ManageUser.verifyUser(user.getUsername(), oldPassword);
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
        ManageUser.changePassword(user.getId(), newPassword);
        String message = "Password changed successfully";
        request.setAttribute("message", message);

        user.setPassword(newPassword);
        AppUtils.storeLoginedUser(request.getSession(), user);
    }
}
