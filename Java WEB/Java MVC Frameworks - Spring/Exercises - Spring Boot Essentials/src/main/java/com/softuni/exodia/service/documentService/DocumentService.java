package com.softuni.exodia.service.documentService;

import com.softuni.exodia.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

    DocumentServiceModel addDocument(DocumentServiceModel serviceModel);

    DocumentServiceModel findById(String id);

    List<DocumentServiceModel> findAll();

    boolean printDocumentById(String id);
}
