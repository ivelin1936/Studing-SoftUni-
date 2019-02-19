package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.PackageBindingModel;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.domain.models.service.UserServiceModel;
import pandaApp.service.packageService.PackageService;
import pandaApp.service.userService.UserService;
import pandaApp.utils.AppConstants;
import pandaApp.utils.IValidator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CreatePackageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PackageBindingModel packageBindingModel;

    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;
    private IValidator validator;

    public CreatePackageBean() {
    }

    @Inject
    public CreatePackageBean(PackageService packageService,
                             UserService userService,
                             ModelMapper modelMapper,
                             IValidator validator) {
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
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

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (!this.validator.isValid(packageServiceModel)) {
            //Send an error message on validation Failure
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(AppConstants.INVALID_PACKAGE_SERVICE_MODEL_FACES_MSG));

            //On failure validation redirect to /packages/create
            context.redirect("/faces/view/packages/create.xhtml");
            return;
        }

        String recipientUsername = this.packageBindingModel.getRecipient();
        UserServiceModel recipientUSM = this.userService.findByUsername(recipientUsername);
        packageServiceModel.setRecipient(recipientUSM);

        this.packageService.create(packageServiceModel);

        //After successfully created package should be redirect to home page
        context.redirect("/faces/view/home.xhtml");
    }
}
