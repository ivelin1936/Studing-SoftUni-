package meTube.web.filters;

import meTube.domain.models.binding.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    private static final String POST_REQUEST = "post";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase(POST_REQUEST)) {
            UserLoginBindingModel model = new UserLoginBindingModel();
            model.setUsername(req.getParameter("username"));
            model.setPassword(req.getParameter("password"));

            req.setAttribute("bindingModel", model);
        }

        filterChain.doFilter(req, resp);
    }
}
