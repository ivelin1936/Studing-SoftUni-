package meTube.web.servlets;

import meTube.domain.models.view.HomeTubeViewModel;
import meTube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

//    private final TubeService tubeService;
//    private final ModelMapper modelMapper;
//
//    @Inject
//    public HomeServlet(TubeService tubeService,
//                       ModelMapper modelMapper) {
//        this.tubeService = tubeService;
//        this.modelMapper = modelMapper;
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<HomeTubeViewModel> homeTubeViewModels = this.tubeService.findAll()
//                .stream()
//                .map(tubeServiceModel -> {
//                    HomeTubeViewModel htvm = this.modelMapper.map(tubeServiceModel, HomeTubeViewModel.class);
//                    htvm.setUploader(tubeServiceModel.getUploader().getUsername());
//                    return htvm;
//                }).collect(Collectors.toList());
//
//        req.setAttribute("viewModels", homeTubeViewModels);
        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
