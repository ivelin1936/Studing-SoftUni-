package chushka.service.impl;

import chushka.domain.entities.Product;
import chushka.domain.entities.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.repository.interfaces.ProductRepo;
import chushka.service.interfaces.ProductService;
import chushka.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepo.save(product);
    }

    @Override
    public List<ProductServiceModel> getAllProducts() {
        List<Product> products = this.productRepo.findAll();

        List<ProductServiceModel> productsView = products.stream()
                .map(product -> {
                    ProductServiceModel psm = this.modelMapper.map(product, ProductServiceModel.class);
                    psm.setType(product.getType().name());
                    return psm;
                })
                .collect(Collectors.toList());

        return productsView;
    }

    @Override
    public ProductServiceModel getByName(String name) {
        Product product = this.productRepo.findByName(name);

        if (product == null) {
            return null;
        }

        ProductServiceModel psm = this.modelMapper.map(product, ProductServiceModel.class);
        psm.setType(product.getType().name());

        return psm;
    }
}
