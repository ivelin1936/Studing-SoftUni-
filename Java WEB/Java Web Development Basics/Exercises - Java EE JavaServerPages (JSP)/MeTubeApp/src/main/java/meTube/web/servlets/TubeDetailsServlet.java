package meTube.web.servlets;

import meTube.domain.models.serviceModels.TubeServiceModel;
import meTube.domain.models.viewModels.TubeDetailsViewModel;
import meTube.service.TubeService;
import meTube.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService,
                              ModelMapper modelMapper) {
        this.tubeService = tubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, String> queryMap = parseQueryParameters(req);
        String tubeName = queryMap.get("name");

        TubeServiceModel tubeServiceModel = this.tubeService.findByName(tubeName);

        if (tubeServiceModel == null) {
            req.setAttribute("tubeName", tubeName);
            req.getRequestDispatcher("/jsps/404-not-found-page.jsp").forward(req, resp);
        } else {
            TubeDetailsViewModel tdvm = this.modelMapper.map(tubeServiceModel, TubeDetailsViewModel.class);

            req.setAttribute("TubeDetailsViewModel", tdvm);
            req.getRequestDispatcher("/jsps/tube-details-page.jsp").forward(req, resp);
        }
    }

    private HashMap<String, String> parseQueryParameters(HttpServletRequest req) throws UnsupportedEncodingException {
        HashMap<String, String> queryMap = new HashMap<>();
        String q = req.getQueryString();
        String queryString = URLDecoder.decode(req.getQueryString(), StandardCharsets.UTF_8);

        Arrays.stream(queryString.split("&"))
                .forEach(qs -> {
                    String[] queryTokens = qs.split("=");
                    queryMap.putIfAbsent(queryTokens[0], queryTokens[1]);
                });

        return queryMap;
    }
}
