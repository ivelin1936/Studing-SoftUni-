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
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CreateCatServlet extends HttpServlet {

    private static final String CREATE_CAT_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\FDMC\\src\\main\\resources\\views\\create.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public CreateCatServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String htmlContent = this.htmlFileReader.readFile(CREATE_CAT_HTML_FILE_PATH);
        writer.println(htmlContent);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String breed = req.getParameter("breed");
        String color = req.getParameter("color");
        int age = Integer.parseInt(req.getParameter("age"));

        Cat cat = new Cat(name, breed, color, age);

        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");
        if (cats == null) {
            cats = new LinkedHashMap<>();
        }

        cats.putIfAbsent(name, cat);
        req.getSession().setAttribute("cats", cats);

        resp.sendRedirect(String.format("/cats/profile?catName=%s", name));
    }
}
