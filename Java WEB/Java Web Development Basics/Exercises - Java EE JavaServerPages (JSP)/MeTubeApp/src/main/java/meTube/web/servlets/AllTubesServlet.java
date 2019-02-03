package meTube.web.servlets;

import meTube.domain.models.serviceModels.TubeServiceModel;
import meTube.domain.models.viewModels.TubeViewModel;
import meTube.service.TubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class AllTubesServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public AllTubesServlet(TubeService tubeService,
                           ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeViewModel> allTSM = this.tubeService.getAll()
                .stream()
                .map(tsm -> this.modelMapper.map(tsm, TubeViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute("AllTubeViewModels", allTSM);

        req.getRequestDispatcher("/jsps/all-tube-page.jsp")
                .forward(req, resp);
    }
}
