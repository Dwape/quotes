package securityFilter;

import hibernate.ManageUser;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UsernameFilter implements Filter {

    public UsernameFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //Executed before the servlet
        //Check to see if the logged in user's username is already saved in the session
        String username = request.getRemoteUser();
        HttpSession session = request.getSession();
        if (username != null && session.getAttribute("username") == null) {
            //Long id = Long.parseLong(userId);
            //String username = ManageUser.getUsername(id);
            session.setAttribute("username", username);
        }

        //Executed with the servlet
        chain.doFilter(request, response);

        //Executed after the servlet
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }
}
