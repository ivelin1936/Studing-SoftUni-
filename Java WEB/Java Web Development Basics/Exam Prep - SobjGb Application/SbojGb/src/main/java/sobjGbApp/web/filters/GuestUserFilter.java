package sobjGbApp.web.filters;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({
        "/faces/view/home.xhtml",
        "/faces/view/add-jobs.xhtml",
        "/faces/view/job-details.xhtml",
        "/faces/view/job-delete.xhtml"
})
public class GuestUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("/faces/view/login.xhtml");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
