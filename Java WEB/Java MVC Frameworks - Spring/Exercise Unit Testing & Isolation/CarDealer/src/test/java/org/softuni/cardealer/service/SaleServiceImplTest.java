package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.*;
import org.softuni.cardealer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceImplTest {

    private static final String DEFAULT_MAKE = "Trabant";
    private static final String DEFAULT_MODEL = "t-630";
    private static final Long DEFAULT_TRAVELLED_DISTANCE = 300000L;
    private static final String DEFAULT_SUPPLIER_NAME = "Someone";
    private static final String DEFAULT_PART_NAME = "PartName";
    private static final BigDecimal DEFAULT_PART_PRICE = BigDecimal.valueOf(200);
    private static final Integer DEFAULT_QUANTITY = 1;

    @Autowired
    private CarSaleRepository carSaleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private PartSaleRepository partSaleRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private ModelMapper modelMapper;
    private SaleService saleService;

    private CarSaleServiceModel carSaleServiceModel;
    private PartSaleServiceModel partSaleServiceModel;

    private CarServiceModel carServiceModel;
    private PartServiceModel partServiceModel;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.saleService = new SaleServiceImpl(this.carSaleRepository, this.partSaleRepository, this.modelMapper);

        this.carSaleServiceModel = new CarSaleServiceModel();
        this.partSaleServiceModel = new PartSaleServiceModel();

        this.carServiceModel = new CarServiceModel();
        this.carServiceModel.setMake(DEFAULT_MAKE);
        this.carServiceModel.setModel(DEFAULT_MODEL);
        this.carServiceModel.setTravelledDistance(DEFAULT_TRAVELLED_DISTANCE);
        this.carServiceModel.setParts(List.of());

        Car saveCar = this.carRepository.save(this.modelMapper.map(this.carServiceModel, Car.class));
        this.carServiceModel = this.modelMapper.map(saveCar, CarServiceModel.class);

        this.partServiceModel = new PartServiceModel();
        this.partServiceModel.setName(DEFAULT_PART_NAME);
        this.partServiceModel.setPrice(DEFAULT_PART_PRICE);

        Part savePart = this.partRepository.save(this.modelMapper.map(this.partServiceModel, Part.class));
        this.partServiceModel = this.modelMapper.map(savePart, PartServiceModel.class);

        Supplier supplier = new Supplier();
        supplier.setName(DEFAULT_SUPPLIER_NAME);
        supplier.setImporter(true);
        Supplier saveSupplier = this.supplierRepository.saveAndFlush(supplier);
        this.partServiceModel.setSupplier(this.modelMapper.map(saveSupplier, SupplierServiceModel.class));
    }

    @Test
    public void saleCar_withValidCar_expectCorrect() {
        this.carSaleServiceModel.setCar(this.carServiceModel);
        this.carSaleServiceModel.setDiscount(20D);

        this.saleService.saleCar(this.carSaleServiceModel);

        long actual = this.carSaleRepository.count();
        long expected = 1L;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void saleCar_withValidCarWithoutDiscount_expectThrow() {
        this.carSaleServiceModel.setCar(this.carServiceModel);
        CarSaleServiceModel saleServiceModel = this.saleService.saleCar(this.carSaleServiceModel);
    }

    @Test(expected = Exception.class)
    public void saleCar_withNullCar_shouldThrow() {
        this.saleService.saleCar(null);
    }

    @Test(expected = Exception.class)
    public void saleCar_withInValidCar_shouldThrow() {
        this.carSaleServiceModel.setCar(null);
        this.saleService.saleCar(null);
    }

    @Test
    public void salePart_withValidPart_expectCorrect() {
        this.partSaleServiceModel.setPart(this.partServiceModel);
        this.partSaleServiceModel.setDiscount(20D);
        this.partSaleServiceModel.setQuantity(DEFAULT_QUANTITY);

        this.saleService.salePart(this.partSaleServiceModel);

        long actual = this.partSaleRepository.count();
        long expected = 1L;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void salePart_withValidPartWithoutQuantity_expectThrow() {
        this.partSaleServiceModel.setPart(this.partServiceModel);
        this.partSaleServiceModel.setDiscount(20D);
        this.saleService.salePart(this.partSaleServiceModel);
    }

    @Test(expected = Exception.class)
    public void saleCar_withValidPartWithoutDiscount_expectThrow() {
        this.partSaleServiceModel.setPart(this.partServiceModel);
        this.saleService.saleCar(this.carSaleServiceModel);
    }

    @Test(expected = Exception.class)
    public void saleCar_withNullPart_shouldThrow() {
        this.saleService.salePart(null);
    }

    @Test(expected = Exception.class)
    public void saleCar_withInValidPart_shouldThrow() {
        this.partSaleServiceModel.setPart(null);
        this.saleService.salePart(null);
    }
}