package meTube.repository.impl;

import meTube.domain.entities.Tube;
import meTube.repository.interfaces.TubeRepo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepoImpl implements TubeRepo {

    private final EntityManager entityManager;

    @Inject
    public TubeRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public Tube findById(String id) {
        Tube tube = this.entityManager
                .createQuery("SELECT t FROM tubes t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
        return tube;
    }

    @Override
    public List<Tube> findAll() {
        List<Tube> tubes = this.entityManager
                .createQuery("SELECT t FROM tubes t", Tube.class)
                .getResultList();
        return tubes;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.getTransaction().begin();
        Tube mergedTube = this.entityManager.merge(tube);
        this.entityManager.getTransaction().commit();
        return mergedTube;
    }
}
