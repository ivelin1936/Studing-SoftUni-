package meTube.web.servlets;

import meTube.domain.models.binding.UserLoginBindingModel;
import meTube.domain.models.service.UserServiceModel;
import meTube.service.interfaces.UserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel model = (UserLoginBindingModel) req.getAttribute("bindingModel");
        UserServiceModel serviceModel = this.userService.findUser(model.getUsername(), model.getPassword());

        if (serviceModel == null) {
            String errorMessage = "Invalid credentials!";
            req.setAttribute("errorMessage", errorMessage);

            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
            return;
        }

        //get the old session and invalidate
        HttpSession oldSession = req.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }

        //generate a new session
        HttpSession newSession = req.getSession(true);
        //setting session to expiry in 30 mins
        newSession.setMaxInactiveInterval(30*60);

//        Cookie cookie = new Cookie("username", serviceModel.getUsername());
//        resp.addCookie(cookie);

        req.getSession().setAttribute("username", serviceModel.getUsername());
        resp.sendRedirect("/home");
    }
}
