package pandaApp.web.managedBeans;

import pandaApp.service.receiptService.ReceiptService;
import pandaApp.utils.AppConstants;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class AcquirePackageBean implements Serializable {
    private static final long serialVersionUID = 1L;

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
        String username = (String) session.getAttribute(AppConstants.USERNAME);

        this.receiptService.createReceipt(username, packageId);

        //Redirect to same URI
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(((HttpServletRequest) context.getRequest()).getRequestURI());
    }
}
