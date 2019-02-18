package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.view.PackageDetailsViewModel;
import pandaApp.service.packageService.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class PackageDetailsBean {

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
                .get("packageId");

        //If dont exist package with passed ID, packageService will throw IllegalArgumentException
        try {
            PackageServiceModel packageServiceModel = this.packageService.findById(packageId);

            this.detailsViewModel = this.modelMapper.map(packageServiceModel, PackageDetailsViewModel.class);

            //Set recipient username
            this.detailsViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            //If have delivery date -> format it, otherwise set to N/A
            String formatDate = packageServiceModel.getEstimatedDeliveryDate() == null ? "N/A"
                    : packageServiceModel.getStatus().equals(Status.Delivered) ? "Delivered"
                    : packageServiceModel.getStatus().equals(Status.Acquired) ? "Delivered"
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
