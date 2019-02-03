package meTube.web.servlets;

import meTube.domain.models.bindingModels.TubeCreateBindingModel;
import meTube.domain.models.serviceModels.TubeServiceModel;
import meTube.service.TubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class CreateTubeServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public CreateTubeServlet(TubeService tubeService,
                             ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/create-tube-page.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeCreateBindingModel tcbm = (TubeCreateBindingModel) req.getAttribute("TubeCreateBindingModel");
        TubeServiceModel serviceModel = this.modelMapper.map(tcbm, TubeServiceModel.class);
        this.tubeService.save(serviceModel);

        resp.sendRedirect(String.format("/tubes/details?name=%s", serviceModel.getName()));
    }
}
