package fdmcApp.repository.impl;

import fdmcApp.domain.entities.Cat;
import fdmcApp.repository.interfaces.CatRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {

    private EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cat save(Cat cat) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cat);
        this.entityManager.getTransaction().commit();

        return cat;
    }

    @Override
    public Cat findById(String id) {
        try {
            return this.entityManager
                    .createQuery("SELECT c FROM Cat c WHERE c.id = :id", Cat.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            //log here...
            return null;
        }
    }

    @Override
    public List<Cat> findAll() {
        return this.entityManager
                .createQuery("SELECT c FROM Cat c", Cat.class)
                .getResultList();
    }
}
