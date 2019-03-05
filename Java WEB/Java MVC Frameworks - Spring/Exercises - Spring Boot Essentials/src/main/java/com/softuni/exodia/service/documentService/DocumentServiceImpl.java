package com.softuni.exodia.service.documentService;

import com.softuni.exodia.domain.entities.Document;
import com.softuni.exodia.domain.models.service.DocumentServiceModel;
import com.softuni.exodia.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final String DEFAULT_INVALID_MODEL_MESSAGE = "Invalid document model!";
    private static final int DEFAULT_CONSTRAINT_VIOLATION_SIZE = 0;

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository,
                               ModelMapper modelMapper,
                               Validator validator) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public DocumentServiceModel addDocument(DocumentServiceModel serviceModel) {
        if (this.validator.validate(serviceModel).size() != DEFAULT_CONSTRAINT_VIOLATION_SIZE) {
            throw new IllegalArgumentException(DEFAULT_INVALID_MODEL_MESSAGE);
        }

        Document documentEntity = this.modelMapper.map(serviceModel, Document.class);
        return this.modelMapper
                .map(this.documentRepository.saveAndFlush(documentEntity), DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id).orElse(null);

        return document == null ? null
                : this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        List<Document> documents = this.documentRepository.findAll();

        return documents.stream()
                .map(doc -> this.modelMapper.map(doc, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean printDocumentById(String id) {
        try {
            this.documentRepository.deleteById(id);
        } catch (IllegalArgumentException | EmptyResultDataAccessException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
