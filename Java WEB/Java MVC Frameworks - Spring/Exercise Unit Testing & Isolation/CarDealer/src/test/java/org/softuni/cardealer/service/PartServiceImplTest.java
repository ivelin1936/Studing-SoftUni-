package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceImplTest {

    private static final String DEFAULT_SUPPLIER_NAME = "Someone";
    private static final String DEFAULT_PART_NAME = "PartName";
    private static final String DEFAULT_PART_NEW_NAME = "NewPartName";
    private static final BigDecimal DEFAULT_PART_PRICE = BigDecimal.valueOf(200);
    private static final BigDecimal DEFAULT_PART_NEW_PRICE = BigDecimal.ONE;
    private static final String DEFAULT_PART_INVALID_ID = "Invalid part id";

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private ModelMapper modelMapper;
    private PartService partService;
    private PartServiceModel partServiceModel;

    @Before
    public void setUp() {
        this.modelMapper = new ModelMapper();
        this.partService = new PartServiceImpl(this.partRepository, this.modelMapper);

        this.partServiceModel = new PartServiceModel();
        this.partServiceModel.setName(DEFAULT_PART_NAME);
        this.partServiceModel.setPrice(DEFAULT_PART_PRICE);

        Supplier supplier = new Supplier();
        supplier.setName(DEFAULT_SUPPLIER_NAME);
        supplier.setImporter(true);
        Supplier saveSupplier = this.supplierRepository.saveAndFlush(supplier);
        this.partServiceModel.setSupplier(this.modelMapper.map(saveSupplier, SupplierServiceModel.class));
    }

    @Test
    public void savePart_withValidPart_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);

        long actual = this.partRepository.count();
        long expected = 1L;

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void savePart_withInValidPart_expectThrow() {
        this.partService.savePart(null);
    }

    @Test(expected = Exception.class)
    public void savePart_withInValidPartName_expectThrow() {
        this.partServiceModel.setName(null);
        this.partService.savePart(this.partServiceModel);
    }

    @Test(expected = Exception.class)
    public void savePart_withInValidPartPrice_expectThrow() {
        this.partServiceModel.setPrice(null);
        this.partService.savePart(this.partServiceModel);
    }

    @Test
    public void editPart_withValidPart_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);

        Part partById = this.partRepository.findById(savePart.getId()).orElse(null);
        PartServiceModel serviceModel = this.modelMapper.map(partById, PartServiceModel.class);
        serviceModel.setName(DEFAULT_PART_NEW_NAME);
        serviceModel.setPrice(DEFAULT_PART_NEW_PRICE);

        PartServiceModel editPart = this.partService.editPart(serviceModel);

        Assert.assertEquals(DEFAULT_PART_NEW_NAME, editPart.getName());
        Assert.assertEquals(DEFAULT_PART_NEW_PRICE, editPart.getPrice());
    }

    @Test(expected = Exception.class)
    public void editPart_withInValidPart_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.editPart(null);
    }

    @Test(expected = Exception.class)
    public void editPart_withInValidNewPartName_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);

        Part partById = this.partRepository.findById(savePart.getId()).orElse(null);
        PartServiceModel serviceModel = this.modelMapper.map(partById, PartServiceModel.class);
        serviceModel.setName(null);
        serviceModel.setPrice(DEFAULT_PART_NEW_PRICE);

        this.partService.editPart(serviceModel);
    }

    @Test(expected = Exception.class)
    public void editPart_withInValidNewPartPrice_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);

        Part partById = this.partRepository.findById(savePart.getId()).orElse(null);
        PartServiceModel serviceModel = this.modelMapper.map(partById, PartServiceModel.class);
        serviceModel.setName(DEFAULT_PART_NEW_NAME);
        serviceModel.setPrice(null);

        this.partService.editPart(serviceModel);
    }

    @Test
    public void deletePart_withValidPartID_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.deletePart(savePart.getId());

        long expected = 0L;
        long actual = this.partRepository.count();

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = Exception.class)
    public void deletePart_withNullPartID_expectThrow() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.deletePart(null);
    }

    @Test(expected = Exception.class)
    public void deletePart_withInValidPartID_expectThrow() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.deletePart(DEFAULT_PART_INVALID_ID);
    }

    @Test
    public void findPartById_withValidPartID_expectCorrect() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);

        PartServiceModel partById = this.partService.findPartById(savePart.getId());

        Assert.assertEquals(savePart.getId(), partById.getId());
        Assert.assertEquals(savePart.getName(), partById.getName());
        Assert.assertEquals(savePart.getPrice(), partById.getPrice());
        Assert.assertEquals(savePart.getSupplier().getId(), partById.getSupplier().getId());
        Assert.assertEquals(savePart.getSupplier().getName(), partById.getSupplier().getName());
    }

    @Test(expected = Exception.class)
    public void findPartById_withNullPartID_expectThrow() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.findPartById(null);
    }

    @Test(expected = Exception.class)
    public void findPartById_withInValidPartID_expectThrow() {
        PartServiceModel savePart = this.partService.savePart(this.partServiceModel);
        this.partService.findPartById(DEFAULT_PART_INVALID_ID);
    }
}