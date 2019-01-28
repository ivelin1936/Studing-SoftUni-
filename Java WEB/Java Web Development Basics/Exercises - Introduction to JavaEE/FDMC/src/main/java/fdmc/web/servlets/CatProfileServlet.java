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
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private final static String PROFILE_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\FDMC\\src\\main\\resources\\views\\profile.html";
    private final static String PROFILE_404_HTML_FILE_PATH = "C:\\Users\\Admin\\Desktop\\FDMC\\src\\main\\resources\\views\\profile404NotFound.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public CatProfileServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getParameter("catName");

        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");

        String profileHtmlFileContent;
        if (cats == null || !cats.containsKey(catName)) {
            profileHtmlFileContent = this.htmlFileReader.readFile(PROFILE_404_HTML_FILE_PATH);
            profileHtmlFileContent = profileHtmlFileContent.replace("{{catName}}", catName);
        } else {
            Cat cat = cats.get(catName);
            profileHtmlFileContent = this.htmlFileReader.readFile(PROFILE_HTML_FILE_PATH);
            profileHtmlFileContent = profileHtmlFileContent.replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", String.valueOf(cat.getAge()));
        }

        resp.getWriter().println(profileHtmlFileContent);
    }
}
