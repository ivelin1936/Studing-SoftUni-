package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
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
public class PendingPackagesBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<PendingPackageViewModel> packagesViewModel;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public PendingPackagesBean() {
    }

    @Inject
    public PendingPackagesBean(PackageService packageService,
                               ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPendingPackages() {
        this.packagesViewModel = this.packageService.findAllByStatus(Status.Pending)
                .stream()
                .map(packageServiceModel -> {
                    PendingPackageViewModel packageViewModel =
                            this.modelMapper.map(packageServiceModel, PendingPackageViewModel.class);
                    packageViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());

                    return packageViewModel;
                })
                .collect(Collectors.toList());
    }

    //Getter for pending package view model
    public List<PendingPackageViewModel> getPackagesViewModel() {
        return this.packagesViewModel;
    }

    //Setter for pending package view model
    public void setPackagesViewModel(List<PendingPackageViewModel> packagesViewModel) {
        this.packagesViewModel = packagesViewModel;
    }

    //Shipping package action
    public void shipPackage(String id) throws IOException {
        boolean isUpdated = this.packageService.updateStatus(id);

        //Redirect to same URI
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(((HttpServletRequest) context.getRequest()).getRequestURI());
    }
}
