package servlet;

import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;






//DEPRECATED









@WebServlet("/log_in")
public class LogInForm extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        // New location to be redirected
        String site = new String("http://localhost:8080/loginForm.jsp");

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
        /*
        PrintWriter out = response.getWriter();
        String title = "Login";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + request.getParameter("last_name") + "\n" +
                "</ul>\n" +
                "</body>" +
                "</html>"
        );
        RequestDispatcher view = request.getRequestDispatcher("webapp/index.html");
        view.forward(request, response);
        */
    }
}
