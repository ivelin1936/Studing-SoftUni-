package meTube.web.servlets;

import meTube.domain.models.service.TubeServiceModel;
import meTube.domain.models.view.TubeDetailsViewModel;
import meTube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/details")
public class DetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public DetailsServlet(TubeService tubeService,
                          ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeId = req.getQueryString().split("=")[1];
        //Find tube by query tube ID
        TubeServiceModel tubeServiceModel = this.tubeService.findById(tubeId);
        //Update (increment) tube's views
        tubeServiceModel = this.tubeService.update(tubeServiceModel);
        //Map serviceModel to needed viewModel
        TubeDetailsViewModel tubeViewModel = this.modelMapper.map(tubeServiceModel, TubeDetailsViewModel.class);
        tubeViewModel.setUploader(tubeServiceModel.getUploader().getUsername());

        //Set the view model as attribute on the request
        req.setAttribute("viewModel", tubeViewModel);

        req.getRequestDispatcher("/jsp/tube-details.jsp").forward(req, resp);
    }
}
