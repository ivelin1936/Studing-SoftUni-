package pandaApp.web.filters;

import pandaApp.utils.AppConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/view/register.xhtml",
        "/faces/view/login.xhtml",
        "/faces/view/index.xhtml",
        "/"
})
public class LoggedInUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //If current user is already loggedIn, should be redirect to home page
        if (session.getAttribute(AppConstants.USERNAME) != null) {
            resp.sendRedirect("/faces/view/home.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
