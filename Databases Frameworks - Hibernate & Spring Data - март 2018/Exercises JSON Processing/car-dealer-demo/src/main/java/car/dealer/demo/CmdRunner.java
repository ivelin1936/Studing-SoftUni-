package car.dealer.demo;

import car.dealer.demo.model.dto.bindingModel.seedDataDtos.*;
import car.dealer.demo.model.entity.Sale;
import car.dealer.demo.service.carService.CarService;
import car.dealer.demo.service.customerService.CustomerService;
import car.dealer.demo.service.partService.PartService;
import car.dealer.demo.service.saleService.SaleService;
import car.dealer.demo.service.supplierService.SupplierService;
import car.dealer.demo.utils.serialize.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class CmdRunner implements CommandLineRunner {

    private final static String CARS_INPUT_JSON = "/files/input/cars.json";
    private final static String CUSTOMERS_INPUT_JSON = "/files/input/customers.json";
    private final static String PARTS_INPUT_JSON = "/files/input/parts.json";
    private final static String SUPPLIERS_INPUT_JSON = "/files/input/suppliers.json";

    private final Serializer serializerJson;
    private final Serializer serializerXml;

    private CarService carService;
    private CustomerService customerService;
    private PartService partService;
    private SaleService saleService;
    private SupplierService supplierService;

    public CmdRunner(
            @Qualifier(value = "JsonSerializer") Serializer serializerJson,
            @Qualifier(value = "XmlSerializer") Serializer serializerXml,
            CarService carService,
            CustomerService customerService,
            PartService partService,
            SaleService saleService,
            SupplierService supplierService) {
        this.serializerJson = serializerJson;
        this.serializerXml = serializerXml;
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.saleService = saleService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDataIntoDB();
    }

    private void seedDataIntoDB() {
        seedSuppliers();
        seedParts();
        seedCars();
        seedCustomers();
        seedRandomSales();
    }

    private void seedRandomSales() {
        List<SaleSeedBindingModel> salesList = new ArrayList<>();
        fillSaleBindingModelsRandomly(salesList);
        this.saleService.saveAll(salesList);
    }

    private void fillSaleBindingModelsRandomly(List<SaleSeedBindingModel> salesList) {
        Random random = new Random();
        ArrayList<Double> discountList = new ArrayList<Double>() {{
            add(0.0);
            add(0.0);
            add(5.0);
            add(10.0);
            add(15.0);
            add(20.0);
            add(30.0);
            add(40.0);
            add(50.0);
        }};

        int index = random.nextInt(600);
        for (int i = 0; i < index; i++) {
            long carId = randomNumber(random, 358);
            long customerID = randomNumber(random, 30);
            double discount = discountList.get(randomNumber(random, 8));
            SaleSeedBindingModel saleModel = new SaleSeedBindingModel(carId, customerID, discount);
            salesList.add(saleModel);
        }
    }

    private int randomNumber(Random random, int count) {
        int id;
        do {
            id = random.nextInt(count);
        } while (id == 0 || id > count);
        return id;
    }

    private void seedCustomers() {
        CustomerSeedDataBindinModel[] customerDtos =
                serializerJson.deserialize(CustomerSeedDataBindinModel[].class, CUSTOMERS_INPUT_JSON);
        this.customerService.saveAll(customerDtos);
    }

    private void seedCars() {
        CarSeedDataBindingModel[] carDtos =
                serializerJson.deserialize(CarSeedDataBindingModel[].class, CARS_INPUT_JSON);
        this.carService.saveAll(carDtos);
    }

    private void seedParts() {
        PartSeedDataBindingModel[] partsDtos =
                serializerJson.deserialize(PartSeedDataBindingModel[].class, PARTS_INPUT_JSON);
        this.partService.saveAll(partsDtos);
    }

    private void seedSuppliers() {
        SuppliersSeedDataBindingModel[] suppliersDtos =
                serializerJson.deserialize(SuppliersSeedDataBindingModel[].class, SUPPLIERS_INPUT_JSON);
        this.supplierService.saveAll(suppliersDtos);
    }
}
