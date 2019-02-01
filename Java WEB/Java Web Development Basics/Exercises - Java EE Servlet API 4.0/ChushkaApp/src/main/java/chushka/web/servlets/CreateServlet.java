package chushka.web.servlets;

import chushka.domain.entities.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.service.interfaces.ProductService;
import chushka.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class CreateServlet extends HttpServlet {

    private final static String CREATE_PRODUCT_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\ChushkaApp\\src\\main\\resources\\views\\create-product.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;

    @Inject
    public CreateServlet(HtmlReader htmlReader,
                         ProductService productService) {
        this.htmlReader = htmlReader;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader.ReadHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH)
                .replace("{{typeOptions}}", this.formatTypeOptions());
        resp.getWriter().println(htmlContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel psm = new ProductServiceModel();
        psm.setName(req.getParameter("name"));
        psm.setDescription(req.getParameter("description"));
        psm.setType(req.getParameter("type"));

        this.productService.saveProduct(psm);

        resp.sendRedirect("/");
    }

    private String formatTypeOptions() {
        StringBuilder options = new StringBuilder();

        Arrays.stream(Type.values())
                .forEach(type -> {
                    options.append(
                            String.format("<option value=\"%s\">%s</option>",
                                    type.name(),
                                    type.name()
                            )
                    ).append(System.lineSeparator());
                });

        return options.toString().trim();
    }
}
