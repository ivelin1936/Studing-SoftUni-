package org.softuni.examApp.service.documentService;

import org.softuni.examApp.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    DocumentServiceModel create(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> findAll();

    boolean removeById(String id);
}
