package pandaApp.web.filters;

import pandaApp.utils.AppConstants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/view/packages/create.xhtml",
        "/faces/view/packages/delivered.xhtml",
        "/faces/view/packages/pending.xhtml",
        "/faces/view/packages/shipped.xhtml"
})
public class AdminUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //If current user isn't loggedIn (it's a guest), should be redirect to login page
        if (session.getAttribute(AppConstants.USERNAME) == null) {
            resp.sendRedirect("/faces/view/login.xhtml");
            return;
        }

        String userRole = (String) session.getAttribute(AppConstants.ROLE);

        //If current user role isn't 'Admin', should be redirect to home page
        if (!AppConstants.ADMIN.equalsIgnoreCase(userRole)) {
            resp.sendRedirect("/faces/view/home.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
