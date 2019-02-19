package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.view.PackageDetailsViewModel;
import pandaApp.service.packageService.PackageService;
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
public class PackageDetailsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PackageDetailsViewModel detailsViewModel;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public PackageDetailsBean() {
    }

    @Inject
    public PackageDetailsBean(PackageService packageService,
                              ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPackageDetails() {
        //Get passed param from the request
        String packageId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(AppConstants.PACKAGE_ID);

        //If dont exist package with passed ID, packageService will throw IllegalArgumentException
        try {
            PackageServiceModel packageServiceModel = this.packageService.findById(packageId);

            this.detailsViewModel = this.modelMapper.map(packageServiceModel, PackageDetailsViewModel.class);

            //Set recipient username
            this.detailsViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMATTER_PATTERN);
            //If have delivery date -> format it, otherwise set to N/A
            String formatDate = packageServiceModel.getEstimatedDeliveryDate() == null ? AppConstants.NO_DATE
                    : packageServiceModel.getStatus().equals(Status.Delivered) ? AppConstants.DELIVERED_PACKAGE_DATE
                    : packageServiceModel.getStatus().equals(Status.Acquired) ? AppConstants.DELIVERED_PACKAGE_DATE
                    : packageServiceModel.getEstimatedDeliveryDate().format(formatter);
            //Set estimate delivery date
            this.detailsViewModel.setEstimatedDeliveryDate(formatDate);

        } catch (IllegalArgumentException ex) {
            //LOG here...
            //TODO -> render 404 Not found page
        }
    }

    //Getter for package details view model
    public PackageDetailsViewModel getDetailsViewModel() {
        return this.detailsViewModel;
    }

    //Setter for package details view model
    public void setDetailsViewModel(PackageDetailsViewModel detailsViewModel) {
        this.detailsViewModel = detailsViewModel;
    }
}
