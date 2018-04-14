package car.dealer.demo.service.customerService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.CustomerSeedDataBindinModel;
import car.dealer.demo.model.entity.Customer;

public interface CustomerService {

    void saveAll(CustomerSeedDataBindinModel[] customerDtos);

    Customer oneById(Long customerID);
}
