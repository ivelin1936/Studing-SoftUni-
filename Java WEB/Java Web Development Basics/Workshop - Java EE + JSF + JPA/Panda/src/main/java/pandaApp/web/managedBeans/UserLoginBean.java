package pandaApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import pandaApp.domain.models.binding.UserLoginBindingModel;
import pandaApp.domain.models.service.UserServiceModel;
import pandaApp.service.userService.UserService;
import pandaApp.utils.AppConstants;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class UserLoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UserLoginBindingModel userLoginBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService,
                         ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    //Getter for user login binding model
    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    //Setter for user login binding model
    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    //Login action
    public void login() throws IOException {
        UserServiceModel userServiceModel = this.userService
                .login(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));

        //If login failed, should show errorMessage with "Invalid credentials."
        if (userServiceModel == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //Send an error message on Login Failure
            facesContext.addMessage(null, new FacesMessage(AppConstants.AUTHENTICATION_FAILED_FACES_MSG));
            return;
        }

        //If login is successful, should set session attributes
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.setAttribute(AppConstants.USERNAME, userServiceModel.getUsername());
        session.setAttribute(AppConstants.ROLE, userServiceModel.getRole());

        //Redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/home.xhtml");
    }
}
