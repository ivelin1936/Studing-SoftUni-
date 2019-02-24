package org.softuni.examApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.models.binding.DocumentScheduleBindingModel;
import org.softuni.examApp.domain.models.service.DocumentServiceModel;
import org.softuni.examApp.service.documentService.DocumentService;
import org.softuni.examApp.util.IValidator;

import javax.annotation.PostConstruct;
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
public class ScheduleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private DocumentScheduleBindingModel bindingModel;

    private DocumentService documentService;
    private ModelMapper modelMapper;
    private IValidator validator;

    public ScheduleBean() {
    }

    @Inject
    public ScheduleBean(DocumentService documentService,
                        ModelMapper modelMapper,
                        IValidator validator) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @PostConstruct
    private void init() {
        this.bindingModel = new DocumentScheduleBindingModel();
    }

    //Getter for document binding model
    public DocumentScheduleBindingModel getBindingModel() {
        return this.bindingModel;
    }

    //Setter for document binding model
    public void setBindingModel(DocumentScheduleBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }

    public void schedule() throws IOException {
        DocumentServiceModel serviceModel =
                this.modelMapper.map(this.bindingModel, DocumentServiceModel.class);

        if (!validator.isValid(serviceModel)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            //Send an error message on Login Failure
            facesContext.addMessage(null, new FacesMessage("Invalid data, please try again!"));
            return;
        }

        DocumentServiceModel createdDocument = this.documentService.create(serviceModel);

        //Redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/details.xhtml?id=" + createdDocument.getId());
    }
}
