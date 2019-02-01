package chushka.repository.interfaces;

import chushka.domain.entities.Product;

public interface ProductRepo extends GenericRepository<Product, String> {

    Product findByName(String name);
}
