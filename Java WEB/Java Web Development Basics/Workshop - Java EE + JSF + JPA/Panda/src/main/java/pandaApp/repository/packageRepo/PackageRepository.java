package pandaApp.repository.packageRepo;

import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.repository.genericRepo.GenericRepository;

import java.util.List;

public interface PackageRepository extends GenericRepository<Package, String> {

    List<Package> findByStatus(Status status);

    List<Package> findAllByUsername(String username);
}
