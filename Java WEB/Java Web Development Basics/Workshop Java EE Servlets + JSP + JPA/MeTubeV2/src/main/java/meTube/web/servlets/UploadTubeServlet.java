package meTube.web.servlets;

import meTube.domain.models.binding.TubeBindingModel;
import meTube.domain.models.service.TubeServiceModel;
import meTube.domain.models.service.UserServiceModel;
import meTube.service.interfaces.TubeService;
import meTube.service.interfaces.UserService;
import meTube.util.YouTubeLinkParser;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/upload")
public class UploadTubeServlet extends HttpServlet {

    private final TubeService tubeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UploadTubeServlet(TubeService tubeService,
                             UserService userService,
                             ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TubeBindingModel bindingModel = (TubeBindingModel) req.getAttribute("bindingModel");
        TubeServiceModel serviceModel = this.modelMapper.map(bindingModel, TubeServiceModel.class);

        //get youTube video ID using linkParser and set it to serviceModel
        String youTubeID = YouTubeLinkParser.getYouTubeID(bindingModel.getYouTubeLink());
        serviceModel.setYouTubeId(youTubeID);
        //Find current logged user and set it for uploader on this tube
        UserServiceModel usm = this.userService.findByName((String) req.getSession().getAttribute("username"));
        serviceModel.setUploader(usm);

        this.tubeService.save(serviceModel);

        resp.sendRedirect("/home");
    }
}
