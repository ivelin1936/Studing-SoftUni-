package pandaApp.web.filters;

import pandaApp.utils.AppConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/view/home.xhtml",
        "/faces/view/receipts.xhtml",
        "/faces/view/receipts/*"
})
public class GuestUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //If current user isn't loggedIn (it's a guest), should be redirect to login page
        if (session.getAttribute(AppConstants.USERNAME) == null) {
            resp.sendRedirect("/faces/view/login.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
