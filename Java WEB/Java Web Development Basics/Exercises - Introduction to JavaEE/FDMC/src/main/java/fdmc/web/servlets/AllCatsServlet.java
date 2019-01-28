package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlFileReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {

    private static final String ALL_CATS_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\FDMC\\src\\main\\resources\\views\\allCats.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public AllCatsServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");

        String htmlContent = this.htmlFileReader.readFile(ALL_CATS_HTML_FILE_PATH);
        if (cats == null || cats.size() == 0) {
            htmlContent = htmlContent.replace(
                    "{{catsContent}}",
                    "<h2>There are no cats. <a href=\"/cats/create\">Create some!</a></h2>"
            );
        } else {
            String catsUl = buildUl(cats);
            htmlContent = htmlContent.replace("{{catsContent}}", catsUl);
        }

        PrintWriter writer = resp.getWriter();
        writer.println(htmlContent);
    }

    private String buildUl(Map<String, Cat> cats) {
        StringBuilder catsUl = new StringBuilder();
        catsUl.append("<ul>").append(System.lineSeparator());

        cats.entrySet().stream()
                .map(entry ->
                        String.format("<a href=\"/cats/profile?catName=%s\">%s</a>",
                                entry.getKey(),
                                entry.getKey())
                )
                .forEach(a ->
                        catsUl.append(String.format("<li>%s</li>", a))
                                .append(System.lineSeparator()));
        catsUl.append("</ul>");
        return catsUl.toString();
    }
}
