package pandaApp.service.packageService;

import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.service.PackageServiceModel;

import java.util.List;

public interface PackageService {

    void create(PackageServiceModel serviceModel);

    List<PackageServiceModel> findAllByStatus(Status status);

    boolean updateStatus(String id);

    PackageServiceModel findById(String packageId);

    List<PackageServiceModel> findAllByUsername(String username);
}
