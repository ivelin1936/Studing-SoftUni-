package sobjGbApp.web.managedBeans;

import sobjGbApp.service.jobApplicationService.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class DeleteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private JobApplicationService jobApplicationService;

    public void remove() throws IOException {
        //Get passed param from the request
        String jobId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        boolean isRemoved = this.jobApplicationService.remove(jobId);

        if (!isRemoved) {
            //TODO -> errorMessage "something went wrong"
            return;
        }

        //After successful removing, should Redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/home.xhtml");
    }
}
