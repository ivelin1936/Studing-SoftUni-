package org.softuni.examApp.repository.documentRepo;

import org.softuni.examApp.domain.entities.Document;
import org.softuni.examApp.repository.genericRepo.GenericRepositoryImpl;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentRepositoryImpl extends GenericRepositoryImpl<Document, String> implements DocumentRepository {

    private static final Logger LOG = Logger.getLogger(DocumentRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }


    @Override
    public boolean removeById(String id) {
        try {
            entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Document> delete = cb.createCriteriaDelete(Document.class);
            Root<Document> e = delete.from(Document.class);
            delete.where(cb.equal(e.get("id"), id));
            int deleted = entityManager.createQuery(delete).executeUpdate();
            entityManager.getTransaction().commit();
            return deleted > 0;
        } catch (IllegalStateException | IllegalArgumentException | PersistenceException e) {
            logger().log(Level.SEVERE, "Failed to delete entity by id " + id, e);
            return false;
        }
    }
}
