package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.UserRegisterBindingModel;
import pandaApp.domain.models.service.UserServiceModel;
import pandaApp.service.userService.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService,
                            ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
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
//            throw new IllegalArgumentException("Passwords doesn't match.");
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //Send an error message on Login Failure
            facesContext.addMessage(null, new FacesMessage("Passwords doesn't match! Please try again."));
            return;
        }

        UserServiceModel serviceModel = this.modelMapper
                .map(this.userRegisterBindingModel, UserServiceModel.class);
        this.userService.register(serviceModel);

        //After successful register, redirect to login page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/login.xhtml");
    }
}
