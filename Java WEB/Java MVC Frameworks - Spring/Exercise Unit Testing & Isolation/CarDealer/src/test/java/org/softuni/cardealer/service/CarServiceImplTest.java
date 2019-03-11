package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceImplTest {

    private static final String DEFAULT_MAKE = "Trabant";
    private static final String DEFAULT_NEW_MAKE = "Varburg";
    private static final String DEFAULT_MODEL = "t-630";
    private static final String DEFAULT_NEW_MODEL = "m-21";
    private static final Long DEFAULT_TRAVELLED_DISTANCE = 300000L;
    private static final Long DEFAULT_NEW_TRAVELLED_DISTANCE = 500000L;
    private static final String DEFAULT_INVALID_ID = "Invalid id";

    @Autowired
    private CarRepository carRepository;

    private ModelMapper modelMapper;
    private CarService carService;
    private CarServiceModel carServiceModel;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.carRepository, this.modelMapper);

        this.carServiceModel = new CarServiceModel();
        this.carServiceModel.setModel(DEFAULT_MODEL);
        this.carServiceModel.setMake(DEFAULT_MAKE);
        this.carServiceModel.setTravelledDistance(DEFAULT_TRAVELLED_DISTANCE);
        this.carServiceModel.setParts(List.of());
    }

    @Test
    public void saveCar_withValidCar_expectCorrect() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);

        long actual = this.carRepository.count();
        long expected = 1L;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void saveCar_withInValidCar_expectThrow() {
        CarServiceModel savedCar = this.carService.saveCar(null);
    }

    @Test(expected = Exception.class)
    public void saveCar_withInValidCarMake_expectThrow() {
        CarServiceModel serviceModel = new CarServiceModel();
        serviceModel.setMake(null);
        serviceModel.setModel(DEFAULT_MODEL);
        serviceModel.setTravelledDistance(DEFAULT_TRAVELLED_DISTANCE);
        serviceModel.setParts(List.of());

        CarServiceModel savedCar = this.carService.saveCar(serviceModel);
    }

    @Test(expected = Exception.class)
    public void saveCar_withInValidCarModel_expectThrow() {
        CarServiceModel serviceModel = new CarServiceModel();
        serviceModel.setMake(DEFAULT_MAKE);
        serviceModel.setModel(null);
        serviceModel.setTravelledDistance(DEFAULT_TRAVELLED_DISTANCE);
        serviceModel.setParts(List.of());

        CarServiceModel savedCar = this.carService.saveCar(serviceModel);
    }

    @Test(expected = Exception.class)
    public void saveCar_withInValidCarTravelledDis_expectThrow() {
        CarServiceModel serviceModel = new CarServiceModel();
        serviceModel.setMake(DEFAULT_MAKE);
        serviceModel.setModel(DEFAULT_MODEL);
        serviceModel.setTravelledDistance(null);
        serviceModel.setParts(List.of());

        CarServiceModel savedCar = this.carService.saveCar(serviceModel);
    }

    @Test
    public void editCar_withValidCar_expectCorrect() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);

        Car carById = this.carRepository.findById(savedCar.getId()).orElse(null);
        CarServiceModel serviceModel = this.modelMapper.map(carById, CarServiceModel.class);
        serviceModel.setModel(DEFAULT_NEW_MODEL);
        serviceModel.setMake(DEFAULT_NEW_MAKE);
        serviceModel.setTravelledDistance(DEFAULT_NEW_TRAVELLED_DISTANCE);

        CarServiceModel editedCar = this.carService.editCar(serviceModel);

        Assert.assertEquals(savedCar.getId(), editedCar.getId());
        Assert.assertEquals(DEFAULT_NEW_MAKE, editedCar.getMake());
        Assert.assertEquals(DEFAULT_NEW_MODEL, editedCar.getModel());
        Assert.assertEquals(DEFAULT_NEW_TRAVELLED_DISTANCE, editedCar.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void editCar_withInValidCar_expectThrow() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);
        CarServiceModel editedCar = this.carService.editCar(null);
    }

    @Test(expected = Exception.class)
    public void editCar_withInValidCarData_expectThrow() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);

        Car carById = this.carRepository.findById(savedCar.getId()).orElse(null);
        CarServiceModel serviceModel = this.modelMapper.map(carById, CarServiceModel.class);
        serviceModel.setModel(null);
        serviceModel.setMake(null);
        serviceModel.setTravelledDistance(DEFAULT_NEW_TRAVELLED_DISTANCE);

        CarServiceModel editedCar = this.carService.editCar(serviceModel);
    }

    @Test
    public void deleteCar_withValidCarId_expectCorrect() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);

        this.carService.deleteCar(savedCar.getId());

        Long expected = 0L;
        Long actual = this.carRepository.count();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deleteCar_withInValidCarId_expectThrow() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);
        this.carService.deleteCar(DEFAULT_INVALID_ID);
    }

    @Test
    public void findCarById_withValidCarId_expectCorrect() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);

        CarServiceModel carById = this.carService.findCarById(savedCar.getId());

        Assert.assertEquals(savedCar.getId(), carById.getId());
        Assert.assertEquals(savedCar.getMake(), carById.getMake());
        Assert.assertEquals(savedCar.getModel(), carById.getModel());
        Assert.assertEquals(savedCar.getTravelledDistance(), carById.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void findCarById_withInValidCarId_expectThrow() {
        CarServiceModel savedCar = this.carService.saveCar(this.carServiceModel);
        this.carService.findCarById(DEFAULT_INVALID_ID);
    }
}