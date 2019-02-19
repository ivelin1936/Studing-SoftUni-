package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.service.ReceiptServiceModel;
import pandaApp.domain.models.view.ReceiptViewModel;
import pandaApp.service.receiptService.ReceiptService;
import pandaApp.utils.AppConstants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserReceiptsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ReceiptViewModel> receiptViewModels;

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public UserReceiptsBean() {
    }

    @Inject
    public UserReceiptsBean(ReceiptService receiptService,
                            ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initReceipts() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);
        String username = (String) session.getAttribute(AppConstants.USERNAME);

        List<ReceiptServiceModel> receipts = this.receiptService.findAllByUsername(username);
        this.receiptViewModels = receipts.stream()
                .map(rServiceModel -> {
                    ReceiptViewModel viewModel = this.modelMapper.map(rServiceModel, ReceiptViewModel.class);
                    //Set recipient username
                    viewModel.setRecipient(rServiceModel.getRecipient().getUsername());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMATTER_PATTERN);
                    String formatDate = rServiceModel.getIssuedOn().format(formatter);
                    //Set issuedOn date
                    viewModel.setIssuedOn(formatDate);

                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    //Getter for receipt view model
    public List<ReceiptViewModel> getReceiptViewModels() {
        return this.receiptViewModels;
    }
}
