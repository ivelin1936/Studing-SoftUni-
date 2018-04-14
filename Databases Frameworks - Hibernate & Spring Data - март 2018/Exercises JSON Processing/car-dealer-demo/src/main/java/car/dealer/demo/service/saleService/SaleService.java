package car.dealer.demo.service.saleService;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.SaleSeedBindingModel;

import java.util.List;

public interface SaleService {

    void saveAll(List<SaleSeedBindingModel> salesList);
}
