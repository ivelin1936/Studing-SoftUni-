package meTube.web.filters;

import meTube.domain.models.bindingModels.TubeCreateBindingModel;
import meTube.utils.ValidatorImpl;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    private static final String POST_REQUEST = "post";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase(POST_REQUEST)) {
            TubeCreateBindingModel tcbm = new TubeCreateBindingModel();
            tcbm.setName(req.getParameter("name"));
            tcbm.setDescription(req.getParameter("description"));
            tcbm.setUploader(req.getParameter("uploader"));
            tcbm.setYouTubeLink(req.getParameter("youTubeLink"));

            req.setAttribute("TubeCreateBindingModel", tcbm);
        }

        filterChain.doFilter(req, resp);
    }
}
