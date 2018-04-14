package car.dealer.demo.service.saleService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.SaleSeedBindingModel;
import car.dealer.demo.model.entity.Car;
import car.dealer.demo.model.entity.Customer;
import car.dealer.demo.model.entity.Sale;
import car.dealer.demo.repository.SaleRepository;
import car.dealer.demo.service.carService.CarService;
import car.dealer.demo.service.customerService.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           CarService carService,
                           CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void saveAll(List<SaleSeedBindingModel> salesList) {
        List<Sale> saleList = new ArrayList<>();
        salesList.forEach(s -> {
            Car car = carService.oneById(s.getCarID());
            Customer customer = customerService.oneById(s.getCustomerID());
            Sale sale = new Sale(car, customer, s.getDiscount());
            saleList.add(sale);
        });
        this.saleRepository.saveAll(saleList);
    }
}
