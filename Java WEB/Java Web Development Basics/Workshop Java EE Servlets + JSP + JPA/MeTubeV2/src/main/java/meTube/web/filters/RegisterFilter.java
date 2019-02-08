package meTube.web.filters;

import meTube.domain.models.binding.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class RegisterFilter implements Filter {

    private static final String POST_REQUEST = "post";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase(POST_REQUEST)) {
            UserRegisterBindingModel userModel = new UserRegisterBindingModel();
            userModel.setUsername(req.getParameter("username"));
            userModel.setPassword(req.getParameter("password"));
            userModel.setConfirmPassword(req.getParameter("confirmPassword"));
            userModel.setEmail(req.getParameter("email"));

            req.setAttribute("bindingModel", userModel);
        }

        filterChain.doFilter(req, resp);
    }
}
