package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.view.HomePackageViewModel;
import pandaApp.service.packageService.PackageService;
import pandaApp.utils.AppConstants;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserHomeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<HomePackageViewModel> pendingViewModels;
    private List<HomePackageViewModel> shippedViewModels;
    private List<HomePackageViewModel> deliveredViewModels;

    private PackageService packageService;
    private ModelMapper modelMapper;

    public UserHomeBean() {
    }

    @Inject
    public UserHomeBean(PackageService packageService,
                        ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initPackages() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);
        String username = (String) session.getAttribute(AppConstants.USERNAME);

        List<PackageServiceModel> serviceModels = this.packageService.findAllByUsername(username);
        allocatePackagesByStatus(serviceModels);
    }

    private void allocatePackagesByStatus(List<PackageServiceModel> serviceModels) {
        this.pendingViewModels = new ArrayList<>();
        this.shippedViewModels = new ArrayList<>();
        this.deliveredViewModels = new ArrayList<>();

        serviceModels.forEach(aPackage -> {
            HomePackageViewModel viewModel = this.modelMapper.map(aPackage, HomePackageViewModel.class);
            if (aPackage.getStatus().equals(Status.Pending)) {
                this.pendingViewModels.add(viewModel);
            } else if (aPackage.getStatus().equals(Status.Shipped)) {
                this.shippedViewModels.add(viewModel);
            } else if (aPackage.getStatus().equals(Status.Delivered)) {
                this.deliveredViewModels.add(viewModel);
            }
        });
    }

    //Getter for home view models with pending status
    public List<HomePackageViewModel> getPendingViewModels() {
        return this.pendingViewModels;
    }

    //Getter for home view models with shipped status
    public List<HomePackageViewModel> getShippedViewModels() {
        return this.shippedViewModels;
    }

    //Getter for home view models with delivered status
    public List<HomePackageViewModel> getDeliveredViewModels() {
        return this.deliveredViewModels;
    }
}
