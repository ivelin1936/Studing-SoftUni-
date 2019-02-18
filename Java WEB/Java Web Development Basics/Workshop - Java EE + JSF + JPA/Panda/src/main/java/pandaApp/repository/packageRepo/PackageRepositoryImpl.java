package pandaApp.repository.packageRepo;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.repository.genericRepo.GenericRepositoryImpl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl extends GenericRepositoryImpl<Package, String> implements PackageRepository {

    private EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Package> findByStatus(Status status) {
        try {
            List<Package> packages = this.entityManager
                    .createQuery("SELECT p FROM Package p WHERE p.status = :status", Package.class)
                    .setParameter("status", status)
                    .getResultList();

            return packages;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            return List.of();
        }
    }

    @Override
    public List<Package> findAllByUsername(String username) {
        try {
            List<Package> packages = this.entityManager
                    .createQuery("SELECT p FROM Package p WHERE p.recipient.username = :username", Package.class)
                    .setParameter("username", username)
                    .getResultList();

            return packages;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            return List.of();
        }
    }
}
