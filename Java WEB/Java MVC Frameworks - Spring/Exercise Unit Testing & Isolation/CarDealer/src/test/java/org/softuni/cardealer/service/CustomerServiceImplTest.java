package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceImplTest {

    private static final String DEFAULT_NAME = "DefaultName";
    private static final String DEFAULT_NEW_NAME = "NewName";
    private static final String DEFAULT_INVALID_ID = "Invalid id";

    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;
    private CustomerService customerService;
    private CustomerServiceModel customerServiceModel;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.customerService = new CustomerServiceImpl(this.customerRepository, this.modelMapper);

        this.customerServiceModel = new CustomerServiceModel();
        this.customerServiceModel.setName(DEFAULT_NAME);
        this.customerServiceModel.setBirthDate(LocalDate.now());
        this.customerServiceModel.setYoungDriver(true);
    }

    @Test
    public void saveCustomer_withValidCustomer_expectCorrect() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);

        Customer customerById = this.customerRepository.findById(saveCustomer.getId()).orElse(null);

        Assert.assertEquals(saveCustomer.getId(), customerById.getId());
        Assert.assertEquals(saveCustomer.getName(), customerById.getName());
        Assert.assertEquals(saveCustomer.getBirthDate(), customerById.getBirthDate());
        Assert.assertTrue(customerById.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void saveCustomer_withInValidCustomer_expectThrow() {
        this.customerService.saveCustomer(null);
    }

    @Test(expected = Exception.class)
    public void saveCustomer_withInValidCustomerName_expectThrow() {
        this.customerServiceModel.setName(null);
        this.customerService.saveCustomer(this.customerServiceModel);
    }

    @Test(expected = Exception.class)
    public void saveCustomer_withInValidCustomerDate_expectThrow() {
        this.customerServiceModel.setBirthDate(null);
        this.customerService.saveCustomer(this.customerServiceModel);
    }

    @Test
    public void editCustomer_withValidCustomer_expectCorrect() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);

        Customer customerById = this.customerRepository.findById(saveCustomer.getId()).orElse(null);
        CustomerServiceModel serviceModel = this.modelMapper.map(customerById, CustomerServiceModel.class);
        serviceModel.setName(DEFAULT_NEW_NAME);
        serviceModel.setBirthDate(LocalDate.now());
        serviceModel.setYoungDriver(false);

        this.customerService.editCustomer(serviceModel);
        Customer actualCustomer = this.customerRepository.findById(saveCustomer.getId()).orElse(null);

        Assert.assertEquals(DEFAULT_NEW_NAME, actualCustomer.getName());
        Assert.assertEquals(serviceModel.getBirthDate(), actualCustomer.getBirthDate());
        Assert.assertFalse(actualCustomer.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void editCustomer_withInValidCustomer_expectThrow() {
        this.customerService.saveCustomer(this.customerServiceModel);
        this.customerService.editCustomer(null);
    }

    @Test(expected = Exception.class)
    public void editCustomer_withInValidCustomerID_expectThrow() {
        this.customerService.saveCustomer(this.customerServiceModel);

        this.customerServiceModel.setId(DEFAULT_INVALID_ID);

        this.customerService.editCustomer(this.customerServiceModel);
    }

    @Test
    public void deleteCustomer_withValidCustomerID_expectCorrect() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);

        this.customerService.deleteCustomer(saveCustomer.getId());

        long expected = 0L;
        long actual = this.customerRepository.count();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deleteCustomer_withInValidCustomerID_expectThrow() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);
        this.customerService.deleteCustomer(DEFAULT_INVALID_ID);
    }

    @Test
    public void findCustomerById_withValidCustomerID_expectCorrect() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);

        CustomerServiceModel customerById = this.customerService.findCustomerById(saveCustomer.getId());

        Assert.assertEquals(saveCustomer.getName(), customerById.getName());
        Assert.assertEquals(saveCustomer.getBirthDate(), customerById.getBirthDate());
        Assert.assertTrue(customerById.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void findCustomerById_withInValidCustomerID_expectCorrect() {
        CustomerServiceModel saveCustomer = this.customerService.saveCustomer(this.customerServiceModel);
        this.customerService.findCustomerById(DEFAULT_INVALID_ID);
    }
}