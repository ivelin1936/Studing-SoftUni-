package org.softuni.examApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.models.service.DocumentServiceModel;
import org.softuni.examApp.domain.models.view.DocumentViewModel;
import org.softuni.examApp.service.documentService.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class DetailsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private DocumentViewModel viewModel;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(DocumentService documentService,
                       ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initViewModel() {
        //Get passed param from the request
        String documentId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        DocumentServiceModel documentServiceModel = this.documentService.findById(documentId);

        if (documentServiceModel == null) {
            //ErrorMessage -> "Document not found"
            //TODO - redirect to 404 not found page
            return;
        }

        this.viewModel = this.modelMapper.map(documentServiceModel, DocumentViewModel.class);
    }

    //Getter for document view model
    public DocumentViewModel getViewModel() {
        return this.viewModel;
    }

    //Setter for document view model
    public void setViewModel(DocumentViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
