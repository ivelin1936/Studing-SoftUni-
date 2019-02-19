package pandaApp.web.managedBeans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class UserLogoutBean implements Serializable {
    private static final long serialVersionUID = 1L;

    //Logout action
    public void logout() throws IOException {
        //Get current session
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        //Invalidate current session
        session.invalidate();

        //Redirect to index page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/index.xhtml");
    }
}
