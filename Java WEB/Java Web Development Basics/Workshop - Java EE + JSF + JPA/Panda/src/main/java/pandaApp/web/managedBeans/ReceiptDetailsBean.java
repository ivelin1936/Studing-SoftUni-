package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.service.ReceiptServiceModel;
import pandaApp.domain.models.view.ReceiptDetailsViewModel;
import pandaApp.service.receiptService.ReceiptService;
import pandaApp.utils.AppConstants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class ReceiptDetailsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private ReceiptDetailsViewModel receiptDetailsViewModel;

    private ReceiptService receiptService;
    private ModelMapper modelMapper;

    public ReceiptDetailsBean() {
    }

    @Inject
    public ReceiptDetailsBean(ReceiptService receiptService,
                              ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initReceiptDetailsViewModel() {
        //Get passed "id" param from the request
        String receiptId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(AppConstants.ID);

        try {
            //Find receipt by passed receiptId
            ReceiptServiceModel receiptServiceModel = this.receiptService.findById(receiptId);
            //Map receiptServiceModel to receiptDetailsViewModel
            this.receiptDetailsViewModel =
                    this.modelMapper.map(receiptServiceModel, ReceiptDetailsViewModel.class);

            //Set recipient username
            this.receiptDetailsViewModel.getaPackage()
                    .setRecipient(receiptServiceModel.getRecipient().getUsername());

            //Format date with wanted formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMATTER_PATTERN);
            this.receiptDetailsViewModel.setIssuedOn(receiptServiceModel.getIssuedOn().format(formatter));
        } catch (IllegalArgumentException ex) {
            //LOG here...
            //TODO -> redirect to 404 Not Found page
        }

    }

    //Getter for receipt details view model
    public ReceiptDetailsViewModel getReceiptDetailsViewModel() {
        return this.receiptDetailsViewModel;
    }
}
