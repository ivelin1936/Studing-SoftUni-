package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.view.ShippedPackageViewModel;
import pandaApp.service.packageService.PackageService;
import pandaApp.utils.AppConstants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ShippedPackagesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ShippedPackageViewModel> packageViewModels;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public ShippedPackagesBean() {
    }

    @Inject
    public ShippedPackagesBean(PackageService packageService,
                               ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initShippedPackages() {
        this.packageViewModels = this.packageService.findAllByStatus(Status.Shipped)
                .stream()
                .map(packageServiceModel -> {
                    ShippedPackageViewModel packageViewModel =
                            this.modelMapper.map(packageServiceModel, ShippedPackageViewModel.class);

                    packageViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConstants.DATE_FORMATTER_PATTERN);
                    String formatDate = packageServiceModel.getEstimatedDeliveryDate().format(formatter);
                    packageViewModel.setEstimatedDeliveryDate(formatDate);

                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    //Getter for shipped package view models
    public List<ShippedPackageViewModel> getPackageViewModels() {
        return this.packageViewModels;
    }

    //Setter for shipped package view models
    public void setPackageViewModels(List<ShippedPackageViewModel> packageViewModels) {
        this.packageViewModels = packageViewModels;
    }

    //Action for delivering package
    public void deliverPackage(String id) throws IOException {
        boolean isUpdated = this.packageService.updateStatus(id);

        //Redirect to same URI
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(((HttpServletRequest) context.getRequest()).getRequestURI());
    }
}
