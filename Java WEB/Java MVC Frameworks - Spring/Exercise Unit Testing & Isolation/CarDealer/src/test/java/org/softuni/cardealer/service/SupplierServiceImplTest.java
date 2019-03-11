package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceImplTest {

    private static final String DEFAULT_SUPPLIER_NAME = "Someone";
    private static final String DEFAULT_NEW_SUPPLIER_NAME = "NewName";
    private static final String DEFAULT_INVALID_ID = "InvalidId";

    @Autowired
    private SupplierRepository supplierRepository;

    private ModelMapper modelMapper;
    private SupplierService supplierService;
    private SupplierServiceModel serviceModel;

    @Before
    public void setUp() throws Exception {
        this.modelMapper = new ModelMapper();

        this.serviceModel = new SupplierServiceModel();
        this.serviceModel.setName(DEFAULT_SUPPLIER_NAME);
        this.serviceModel.setImporter(true);

        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    @Test
    public void saveSupplier_withValidSupplier_expectCorrect() {
        SupplierServiceModel savedSupplier = this.supplierService.saveSupplier(this.serviceModel);

        long actual = this.supplierRepository.count();
        long expected = 1L;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void saveSupplier_withInValidSupplier_shouldThrow() {
        SupplierServiceModel supplierServiceModel = new SupplierServiceModel();
        supplierServiceModel.setName(null);
        supplierServiceModel.setImporter(true);

        this.supplierRepository.saveAndFlush(this.modelMapper.map(supplierServiceModel, Supplier.class));
    }

    @Test(expected = Exception.class)
    public void saveSupplier_withNullSupplier_shouldThrow() {
        SupplierServiceModel supplierServiceModel = null;
        this.supplierService.saveSupplier(supplierServiceModel);
    }

    @Test(expected = Exception.class)
    public void editSupplier_withNullSupplier_shouldThrow() {
        SupplierServiceModel supplierServiceModel = null;
        this.supplierService.editSupplier(supplierServiceModel);
    }

    @Test
    public void editSupplier_withCorrectSupplier_expectCorrect() {
        SupplierServiceModel savedModel = this.supplierService.saveSupplier(this.serviceModel);

        Supplier supplier = this.supplierRepository.findById(savedModel.getId()).orElse(null);
        SupplierServiceModel serviceModelById = this.modelMapper.map(supplier, SupplierServiceModel.class);
        serviceModelById.setName(DEFAULT_NEW_SUPPLIER_NAME);
        serviceModelById.setImporter(false);

        SupplierServiceModel editedModel = this.supplierService.editSupplier(serviceModelById);

        Assert.assertEquals(editedModel.getName(), DEFAULT_NEW_SUPPLIER_NAME);
        Assert.assertFalse(editedModel.isImporter());
    }

    @Test
    public void deleteSupplier_withCorrectSupplierId_expectCorrect() {
        SupplierServiceModel savedModel = this.supplierService.saveSupplier(this.serviceModel);

        this.supplierService.deleteSupplier(savedModel.getId());

        long actual = this.supplierRepository.count();
        long expected = 0;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deleteSupplier_withInCorrectSupplierId_shouldThrow() {
        this.supplierRepository.saveAndFlush(this.modelMapper.map(this.serviceModel, Supplier.class));
        this.supplierService.deleteSupplier(DEFAULT_INVALID_ID);
    }

    @Test
    public void findSupplierById() {
        SupplierServiceModel savedModel = this.supplierService.saveSupplier(this.serviceModel);

        SupplierServiceModel supplierById = this.supplierService.findSupplierById(savedModel.getId());

        Assert.assertEquals(supplierById.getId(), savedModel.getId());
        Assert.assertEquals(supplierById.getName(), savedModel.getName());
        Assert.assertEquals(supplierById.isImporter(), savedModel.isImporter());
    }

    @Test(expected = Exception.class)
    public void findSupplierById_withInCorrectSupplierId_shouldThrow() {
        this.supplierRepository.saveAndFlush(this.modelMapper.map(this.serviceModel, Supplier.class));
        this.supplierService.findSupplierById(DEFAULT_INVALID_ID);
    }
}