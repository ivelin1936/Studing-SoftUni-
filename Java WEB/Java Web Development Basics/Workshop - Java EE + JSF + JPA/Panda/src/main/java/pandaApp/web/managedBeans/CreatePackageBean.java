package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.PackageBindingModel;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.service.UserServiceModel;
import pandaApp.service.packageService.PackageService;
import pandaApp.service.userService.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreatePackageBean {

    private PackageBindingModel packageBindingModel;

    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;

    public CreatePackageBean() {
    }

    @Inject
    public CreatePackageBean(PackageService packageService,
                             UserService userService,
                             ModelMapper modelMapper) {
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.packageBindingModel = new PackageBindingModel();
    }

    //Getter for package binding model
    public PackageBindingModel getPackageBindingModel() {
        return this.packageBindingModel;
    }

    //Setter for package binding model
    public void setPackageBindingModel(PackageBindingModel packageBindingModel) {
        this.packageBindingModel = packageBindingModel;
    }

    public List<String> getAllRecipients() {
        return this.userService.findAll()
                .stream()
                .map(UserServiceModel::getUsername)
                .collect(Collectors.toList());
    }

    //Create action
    public void create() throws IOException {
        PackageServiceModel packageServiceModel =
                this.modelMapper.map(this.packageBindingModel, PackageServiceModel.class);

        String recipientUsername = this.packageBindingModel.getRecipient();
        UserServiceModel recipientUSM = this.userService.findByUsername(recipientUsername);
        packageServiceModel.setRecipient(recipientUSM);

        this.packageService.create(packageServiceModel);

        //After successfully created package should be redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/home.xhtml");
    }
}
