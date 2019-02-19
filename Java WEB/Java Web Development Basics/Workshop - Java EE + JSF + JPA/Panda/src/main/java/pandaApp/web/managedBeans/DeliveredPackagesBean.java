package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.view.DeliveredPackageViewModel;
import pandaApp.domain.models.view.PendingPackageViewModel;
import pandaApp.service.packageService.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class DeliveredPackagesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DeliveredPackageViewModel> packagesViewModel;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public DeliveredPackagesBean() {
    }

    @Inject
    public DeliveredPackagesBean(PackageService packageService,
                                 ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPendingPackages() {
        this.packagesViewModel = this.packageService.findAllByStatus(Status.Delivered)
                .stream()
                .map(packageServiceModel -> {
                    DeliveredPackageViewModel packageViewModel =
                            this.modelMapper.map(packageServiceModel, DeliveredPackageViewModel.class);
                    packageViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    //Getter for delivered package view model
    public List<DeliveredPackageViewModel> getPackagesViewModel() {
        return this.packagesViewModel;
    }

    //Setter for delivered package view model
    public void setPackagesViewModel(List<DeliveredPackageViewModel> packagesViewModel) {
        this.packagesViewModel = packagesViewModel;
    }
}
