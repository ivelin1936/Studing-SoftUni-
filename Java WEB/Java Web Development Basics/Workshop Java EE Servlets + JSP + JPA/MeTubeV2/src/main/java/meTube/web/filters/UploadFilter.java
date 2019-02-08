package meTube.web.filters;

import meTube.domain.models.binding.TubeBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/upload")
public class UploadFilter implements Filter {

    private static final String POST_REQUEST = "post";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase(POST_REQUEST)) {
            TubeBindingModel model = new TubeBindingModel();
            model.setTitle(req.getParameter("title"));
            model.setAuthor(req.getParameter("author"));
            model.setYouTubeLink(req.getParameter("youTubeLink"));
            model.setDescription(req.getParameter("description"));

            req.setAttribute("bindingModel", model);
        }

        filterChain.doFilter(req, resp);
    }
}
