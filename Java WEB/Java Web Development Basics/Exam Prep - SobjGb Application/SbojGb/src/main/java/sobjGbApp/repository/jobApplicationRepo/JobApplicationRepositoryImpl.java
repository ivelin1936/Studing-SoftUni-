package sobjGbApp.repository.jobApplicationRepo;

import sobjGbApp.domain.entities.JobApplication;
import sobjGbApp.repository.genericRepo.GenericRepositoryImpl;

import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobApplicationRepositoryImpl extends GenericRepositoryImpl<JobApplication, String> implements JobApplicationRepository {

    private static final Logger LOG = Logger.getLogger(JobApplicationRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public boolean deleteById(String id) {
        try {
            this.entityManager.getTransaction().begin();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<JobApplication> delete = cb.createCriteriaDelete(JobApplication.class);
            Root<JobApplication> e = delete.from(JobApplication.class);
            delete.where(cb.equal(e.get("id"), id));
            int deleted = entityManager.createQuery(delete).executeUpdate();
            this.entityManager.getTransaction().commit();
            return deleted > 0;
        } catch (IllegalStateException
                | IllegalArgumentException
                | PersistenceException ex) {
            logger().log(Level.SEVERE, "Failed to delete entity by id " + id, ex);
            return false;
        }
    }
}
