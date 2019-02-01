package chushka.repository.impl;

import chushka.domain.entities.Product;
import chushka.repository.interfaces.ProductRepo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepoImpl implements ProductRepo {

    private EntityManager entityManager;

    public ProductRepoImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("Cushka")
                .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Product findById(String id) {
        this.entityManager.getTransaction().begin();

        Product product = (Product) this.entityManager
                .createQuery("SELECT p FROM products p WHERE p.id = :id")
                .setParameter("id", id);

        this.entityManager.getTransaction().commit();

        return product;
    }

    @Override
    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();

        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Product findByName(String name) {
        this.entityManager.getTransaction().begin();

        List<Product> productsResultList = this.entityManager
                .createQuery("SELECT p FROM products p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();

        this.entityManager.getTransaction().commit();

        if (productsResultList.isEmpty()) {
            return null;
        }

        return productsResultList.get(0);
    }
}
