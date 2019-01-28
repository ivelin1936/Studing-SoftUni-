package fdmc.web.servlets;

import fdmc.util.HtmlFileReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    private static final String INDEX_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\FDMC\\src\\main\\resources\\views\\index.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public IndexServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String indexHtmlContent = this.htmlFileReader.readFile(INDEX_HTML_FILE_PATH);
        writer.println(indexHtmlContent);
    }
}
