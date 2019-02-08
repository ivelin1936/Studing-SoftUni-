package meTube.web.servlets;

import meTube.domain.models.service.UserServiceModel;
import meTube.service.interfaces.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public ProfileServlet(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggedUserName = (String) req.getSession().getAttribute("username");
        UserServiceModel serviceModel = this.userService.findByName(loggedUserName);

        req.setAttribute("model", serviceModel);

        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}
