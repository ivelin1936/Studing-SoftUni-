package org.softuni.examApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.models.service.DocumentServiceModel;
import org.softuni.examApp.service.documentService.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class DeleteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DeleteBean() {
    }

    @Inject
    public DeleteBean(DocumentService documentService,
                      ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    public void remove() throws IOException {
        //Get passed param from the request
        String documentId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        boolean isRemoved = this.documentService.removeById(documentId);

        //Redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/home.xhtml");
    }
}
