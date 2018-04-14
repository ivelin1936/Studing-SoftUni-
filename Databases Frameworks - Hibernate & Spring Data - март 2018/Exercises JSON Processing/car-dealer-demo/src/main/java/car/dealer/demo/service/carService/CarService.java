package car.dealer.demo.service.carService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.CarSeedDataBindingModel;
import car.dealer.demo.model.entity.Car;

public interface CarService {

    void saveAll(CarSeedDataBindingModel[] carDtos);

    Car oneById(Long carID);
}
