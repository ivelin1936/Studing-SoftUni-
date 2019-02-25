package org.softuni.examApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.models.view.DocumentHomeViewModel;
import org.softuni.examApp.service.documentService.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<DocumentHomeViewModel> viewModels;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentService documentService,
                    ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initModels() {
        this.viewModels = this.documentService.findAll()
                .stream()
                .map(serviceModel -> {
                    DocumentHomeViewModel viewModel = this.modelMapper.map(serviceModel, DocumentHomeViewModel.class);

                    if (serviceModel.getTitle().length() > 12) {
                        String cuttedTitle = serviceModel.getTitle().substring(0, 12) + "...";
                        viewModel.setTitle(cuttedTitle);
                    }

                    return viewModel;
                }).collect(Collectors.toList());

    }

    //Getter for documents view models
    public List<DocumentHomeViewModel> getViewModels() {
        return this.viewModels;
    }

    //Setter for documents view models
    public void setViewModels(List<DocumentHomeViewModel> viewModels) {
        this.viewModels = viewModels;
    }
}
