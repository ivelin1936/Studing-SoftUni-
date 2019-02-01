package chushka.service.interfaces;

import chushka.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> getAllProducts();

    ProductServiceModel getByName(String name);
}
