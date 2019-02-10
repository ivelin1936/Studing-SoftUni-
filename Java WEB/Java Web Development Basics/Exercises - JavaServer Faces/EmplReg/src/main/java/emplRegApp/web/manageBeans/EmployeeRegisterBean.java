package emplRegApp.web.manageBeans;

import emplRegApp.domain.models.service.EmployeeServiceModel;
import emplRegApp.domain.models.view.EmployeeRegisterBindingModel;
import emplRegApp.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class EmployeeRegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmployeeRegisterBindingModel employeeRegisterBindingModel;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeRegisterBean() {
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    @Inject
    public EmployeeRegisterBean(EmployeeService employeeService,
                                ModelMapper modelMapper) {
        this();
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    //Getter for binding model
    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return this.employeeRegisterBindingModel;
    }

    //Setter for binding model
    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }

    public void register() throws IOException {
        //Mapping the binding model to service model
        EmployeeServiceModel serviceModel = this.modelMapper
                .map(this.employeeRegisterBindingModel, EmployeeServiceModel.class);
        //Save the service model into database
        this.employeeService.save(serviceModel);

        //Redirect to "/"
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/");
    }
}
