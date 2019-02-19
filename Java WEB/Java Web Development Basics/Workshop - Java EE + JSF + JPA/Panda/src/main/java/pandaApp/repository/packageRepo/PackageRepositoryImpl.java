package pandaApp.repository.packageRepo;

import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.repository.genericRepo.GenericRepositoryImpl;
import pandaApp.utils.AppConstants;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PackageRepositoryImpl extends GenericRepositoryImpl<Package, String> implements PackageRepository {

    private static final Logger LOG = Logger.getLogger(PackageRepositoryImpl.class.getName());

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public List<Package> findByStatus(Status status) {
        try {
            List<Package> packages = super.entityManager
                    .createQuery("SELECT p FROM Package p WHERE p.status = :status", Package.class)
                    .setParameter(AppConstants.STATUS, status)
                    .getResultList();

            return packages;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            LOG.log(Level.SEVERE, "No Packages found by status: " + status.name(), ex);
            return List.of();
        }
    }

    @Override
    public List<Package> findAllByUsername(String username) {
        try {
            List<Package> packages = super.entityManager
                    .createQuery("SELECT p FROM Package p WHERE p.recipient.username = :username", Package.class)
                    .setParameter(AppConstants.USERNAME, username)
                    .getResultList();

            return packages;
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            LOG.log(Level.SEVERE, "No Packages found by username: " + username, ex);
            return List.of();
        }
    }
}
