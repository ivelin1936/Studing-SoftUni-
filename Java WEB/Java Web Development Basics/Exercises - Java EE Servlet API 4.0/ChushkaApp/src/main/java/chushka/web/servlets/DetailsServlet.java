package chushka.web.servlets;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductDetailsViewModel;
import chushka.service.interfaces.ProductService;
import chushka.utils.HtmlReader;
import chushka.utils.ModelMapper;

import javax.ejb.EJBs;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/products/details")
public class DetailsServlet extends HttpServlet {

    private final static String PRODUCT_DETAILS_HTML_PAGE_PATH = "C:\\Users\\Admin\\Desktop\\ChushkaApp\\src\\main\\resources\\views\\product-details.html";
    private final static String PRODUCT_DETAILS_HTML_404_PAGE_PATH = "C:\\Users\\Admin\\Desktop\\ChushkaApp\\src\\main\\resources\\views\\product-details-404.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public DetailsServlet(HtmlReader htmlReader,
                          ProductService productService,
                          ModelMapper modelMapper) {
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryString = req.getQueryString();
        String productQueryName = queryString.split("=")[1];
        String productDecodedName = URLDecoder.decode(productQueryName, "UTF-8");

        ProductServiceModel psm = this.productService.getByName(productDecodedName);

        if (psm == null) {
            String html404Content = this.htmlReader.ReadHtmlFile(PRODUCT_DETAILS_HTML_404_PAGE_PATH)
                    .replace("{{productName}}", productDecodedName);

            resp.setStatus(404);
            resp.getWriter().println(html404Content);
        } else {
            ProductDetailsViewModel pdvm = this.modelMapper.map(psm, ProductDetailsViewModel.class);

            String htmlContent = this.htmlReader.ReadHtmlFile(PRODUCT_DETAILS_HTML_PAGE_PATH)
                    .replace("{{productName}}", pdvm.getName())
                    .replace("{{productDescription}}", pdvm.getDescription())
                    .replace("{{productType}}", pdvm.getType());

            resp.getWriter().println(htmlContent);
        }
    }
}
