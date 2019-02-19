package pandaApp.repository.genericRepo;

import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GenericRepositoryImpl<E, I> implements GenericRepository<E, I> {

    private final Class<E> entityClass;

    @Inject
    protected EntityManager entityManager;

    protected GenericRepositoryImpl() {
        entityClass = initEntityClass();
    }

    protected abstract Logger logger();

    @SuppressWarnings("unchecked")
    private Class<E> initEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public E save(E entity) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();

            return entity;
        } catch (EntityExistsException
                | IllegalArgumentException
                | TransactionRequiredException ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Failed to create new entity: " + entity, ex);
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public E update(E entity) {
        try {
            this.entityManager.getTransaction().begin();
            E mergedEntity = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return mergedEntity;
        } catch (IllegalArgumentException | TransactionRequiredException ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Entity merge failed: " + entity, ex);
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public boolean delete(E entity) {
        try {
            this.entityManager.remove(entity);
            return true;
        } catch (TransactionRequiredException | IllegalArgumentException ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Entity remove failed: " + entity, ex);
            return false;
        }
    }

    @Override
    public List<E> findAll() {
        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<E> cq = cb.createQuery(this.entityClass);
            Root<E> rootEntry = cq.from(this.entityClass);
            CriteriaQuery<E> all = cq.select(rootEntry);
            TypedQuery<E> allQuery = this.entityManager.createQuery(all);

            return allQuery.getResultList();
        } catch (IllegalStateException | IllegalArgumentException ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Retrieving of all entities failed", ex);
            //Returns an unmodifiable list containing zero elements.
            return List.of();
        }
    }

    @Override
    public E findById(I id) {
        try {
            E entity = this.entityManager.find(entityClass, id);

            return entity;
        } catch (IllegalArgumentException ex) {
            //LOG here...
            logger().log(Level.SEVERE, "Invalid arguments for find entity provided: " + id, ex);
            return null;
        }
    }
}
