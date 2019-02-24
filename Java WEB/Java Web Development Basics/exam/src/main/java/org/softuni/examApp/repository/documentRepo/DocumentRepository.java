package org.softuni.examApp.repository.documentRepo;

import org.softuni.examApp.domain.entities.Document;
import org.softuni.examApp.repository.genericRepo.GenericRepository;

public interface DocumentRepository extends GenericRepository<Document, String> {

    boolean removeById(String id);
}
