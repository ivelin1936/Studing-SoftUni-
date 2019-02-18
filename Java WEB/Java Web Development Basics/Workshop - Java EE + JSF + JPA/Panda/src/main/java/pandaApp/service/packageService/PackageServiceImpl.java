package pandaApp.service.packageService;

import org.modelmapper.ModelMapper;
import pandaApp.domain.entities.Package;
import pandaApp.domain.entities.enumerations.Status;
import pandaApp.domain.models.service.PackageServiceModel;
import pandaApp.repository.packageRepo.PackageRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;
    private ModelMapper modelMapper;

    @Inject
    public PackageServiceImpl(PackageRepository packageRepository,
                              ModelMapper modelMapper) {
        this.packageRepository = packageRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void create(PackageServiceModel serviceModel) {
        Package aPackage = this.modelMapper.map(serviceModel, Package.class);
        aPackage.setStatus(Status.Pending);
        this.packageRepository.save(aPackage);
    }

    @Override
    public List<PackageServiceModel> findAllByStatus(Status status) {
        List<PackageServiceModel> packageServiceModels = this.packageRepository.findByStatus(status)
                .stream()
                .map(aPackage -> this.modelMapper.map(aPackage, PackageServiceModel.class))
                .collect(Collectors.toList());

        return packageServiceModels;
    }

    @Override
    public boolean updateStatus(String id) {
        Package aPackage = this.packageRepository.findById(id);
        this.changeStatus(aPackage);
        this.changeDeliveryDate(aPackage);
        Package updatedPacked = this.packageRepository.update(aPackage);

        return updatedPacked != null;
    }

    @Override
    public PackageServiceModel findById(String packageId) {
        Package aPackage = this.packageRepository.findById(packageId);

        if (aPackage == null) {
            throw new IllegalArgumentException("Don't exist package with this ID into DB.");
        } else {
            return this.modelMapper.map(aPackage, PackageServiceModel.class);
        }
    }

    @Override
    public List<PackageServiceModel> findAllByUsername(String username) {
        List<Package> packages = this.packageRepository.findAllByUsername(username);

        return packages.stream()
                .map(p -> this.modelMapper.map(p, PackageServiceModel.class))
                .collect(Collectors.toList());
    }

    private void changeDeliveryDate(Package aPackage) {
        long days = (System.currentTimeMillis() % 21) + 20;
        aPackage.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(days));
    }

    private void changeStatus(Package aPackage) {
        int updatedStatusIndex = aPackage.getStatus().ordinal() + 1;
        aPackage.setStatus(Status.values()[updatedStatusIndex]);
    }
}
