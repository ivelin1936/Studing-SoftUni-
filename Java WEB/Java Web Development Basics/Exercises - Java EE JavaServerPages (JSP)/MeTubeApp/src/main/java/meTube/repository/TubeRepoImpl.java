package meTube.repository;

import meTube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepoImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepoImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("MeTube")
                .createEntityManager();
    }

    @Override
    public Tube findByName(String name) {
        List<Tube> tubesResultList = this.entityManager
                .createQuery("SELECT t FROM tubes t WHERE t.name = :name", Tube.class)
                .setParameter("name", name)
                .getResultList();

        if (tubesResultList.isEmpty()) {
            return null;
        }

        return tubesResultList.get(0);
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
         Tube tube = (Tube) this.entityManager
                .createQuery("SELECT t FROM tubes t WHERE t.id = :id")
                .setParameter("id", id);

        return tube;
    }

    @Override
    public List<Tube> findAll() {
        List<Tube> tubes = this.entityManager
                .createQuery("SELECT t FROM tubes t", Tube.class)
                .getResultList();

        return tubes;
    }
}
