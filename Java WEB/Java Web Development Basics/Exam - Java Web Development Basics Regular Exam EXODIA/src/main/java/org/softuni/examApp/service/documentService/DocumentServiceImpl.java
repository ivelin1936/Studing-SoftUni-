package org.softuni.examApp.service.documentService;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.entities.Document;
import org.softuni.examApp.domain.models.service.DocumentServiceModel;
import org.softuni.examApp.repository.documentRepo.DocumentRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository,
                               ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel create(DocumentServiceModel documentServiceModel) {
        Document document = this.modelMapper.map(documentServiceModel, Document.class);
        Document documentEntity = this.documentRepository.save(document);

        if (documentEntity == null) {
            return null;
        }

        return this.modelMapper.map(documentEntity, DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel findById(String id) {
        Document document = this.documentRepository.findById(id);

        if (document == null) {
            return null;
        }

        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAll() {
        return this.documentRepository.findAll()
                .stream()
                .map(documentEntity -> this.modelMapper.map(documentEntity, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeById(String id) {
        return this.documentRepository.removeById(id);
    }
}
