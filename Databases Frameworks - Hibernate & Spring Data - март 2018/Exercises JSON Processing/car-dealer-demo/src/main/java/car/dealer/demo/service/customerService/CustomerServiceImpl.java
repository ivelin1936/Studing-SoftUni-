package car.dealer.demo.service.customerService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.CustomerSeedDataBindinModel;
import car.dealer.demo.model.entity.Customer;
import car.dealer.demo.repository.CustomerRepository;
import car.dealer.demo.utils.modelMapper.DtoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveAll(CustomerSeedDataBindinModel[] customerDtos) {
        Arrays.stream(customerDtos)
                .map(c -> DtoConvertUtil.convert(c, Customer.class))
                .forEach(this.customerRepository::saveAndFlush);
    }

    @Override
    public Customer oneById(Long customerID) {
        return this.customerRepository.getOne(customerID);
    }
}
