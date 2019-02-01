package chushka.web.servlets;

import chushka.domain.models.service.ProductServiceModel;
import chushka.domain.models.view.ProductViewModel;
import chushka.service.interfaces.ProductService;
import chushka.utils.HtmlReader;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private final static String INDEX_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\ChushkaApp\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.ReadHtmlFile(INDEX_HTML_FILE_PATH);

        List<ProductViewModel> products = this.productService.getAllProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());

        htmlContent = buildProductsViewUL(htmlContent, products);

        resp.getWriter().println(htmlContent);
    }

    private String buildProductsViewUL(String htmlContent, List<ProductViewModel> products) {
        if (products.isEmpty()) {
            return htmlContent.replace(
                    "{{productsUL}}",
                    "<p style=\"color: red\">No products into DataBase</p>"
            );
        } else {
            String ulContent = "<ul>" +
                    products.stream()
                            .map(p -> String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>",
                                    p.getName(),
                                    p.getName()))
                            .collect(Collectors.joining(System.lineSeparator())) +
                    "</ul>";

            return htmlContent.replace("{{productsUL}}", ulContent);
        }
    }
}
