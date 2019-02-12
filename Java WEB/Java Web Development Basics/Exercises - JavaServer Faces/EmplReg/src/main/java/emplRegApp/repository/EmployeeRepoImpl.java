package emplRegApp.repository;

import emplRegApp.domain.entities.Employee;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeRepoImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    @Inject
    public EmployeeRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee save(Employee entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Employee findById(String id) {
        return this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager
                .createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }
}
