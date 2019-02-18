package pandaApp.web.managedBeans;

import pandaApp.service.receiptService.ReceiptService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class AcquirePackageBean {

    private ReceiptService receiptService;

    public AcquirePackageBean() {
    }

    @Inject
    public AcquirePackageBean(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public void acquirePackage(String packageId) throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);
        String username = (String) session.getAttribute("username");

        this.receiptService.createReceipt(username, packageId);

        //Redirect to same URI
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(((HttpServletRequest) context.getRequest()).getRequestURI());
    }
}
