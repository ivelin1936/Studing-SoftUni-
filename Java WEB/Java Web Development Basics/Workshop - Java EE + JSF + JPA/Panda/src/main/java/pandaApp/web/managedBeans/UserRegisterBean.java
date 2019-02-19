package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.UserRegisterBindingModel;
import pandaApp.domain.models.service.UserServiceModel;
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

@Named
@RequestScoped
public class UserRegisterBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;
    private IValidator validator;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService,
                            ModelMapper modelMapper,
                            IValidator validator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    //Getter for user register binding model
    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    //Setter for user register binding model
    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    //Register action
    public void register() throws IOException {
        String password = this.userRegisterBindingModel.getPassword();
        String confirmPassword = this.userRegisterBindingModel.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //Send an error message on Login Failure
            facesContext.addMessage(null, new FacesMessage(AppConstants.PASSWORDS_DOES_NOT_MATCH_FACES_MSG));
            return;
        }

        UserServiceModel serviceModel = this.modelMapper
                .map(this.userRegisterBindingModel, UserServiceModel.class);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (this.validator.isValid(serviceModel)) {
            this.userService.register(serviceModel);

            //After successful register, redirect to login page
            context.redirect("/faces/view/login.xhtml");
        } else {
            //Send an error message on validation Failure
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(AppConstants.INVALID_USER_SERVICE_MODEL_FACES_MSG));

            //On failure validation redirect to register
            context.redirect("/faces/view/register.xhtml");
        }
    }
}
