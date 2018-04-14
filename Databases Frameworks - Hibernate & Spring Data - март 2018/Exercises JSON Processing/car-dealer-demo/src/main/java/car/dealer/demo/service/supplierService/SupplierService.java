package car.dealer.demo.service.supplierService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.SuppliersSeedDataBindingModel;
import car.dealer.demo.model.entity.Supplier;

public interface SupplierService {

    void saveAll(SuppliersSeedDataBindingModel[] suppliersDtos);

    Supplier findOneById(Long id);
}
