package meTube.web.servlets;

import meTube.domain.models.binding.UserRegisterBindingModel;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public RegisterServlet(UserService userService,
                           ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingModel model = (UserRegisterBindingModel) req.getAttribute("bindingModel");

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            resp.sendRedirect("/register");
            return;
        }

        UserServiceModel serviceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.register(serviceModel);

        resp.sendRedirect("/login");
    }
}
