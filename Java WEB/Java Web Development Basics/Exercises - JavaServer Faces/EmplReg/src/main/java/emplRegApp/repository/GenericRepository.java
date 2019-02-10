package emplRegApp.repository;

import java.util.List;

public interface GenericRepository<E, I> {

    E save(E entity);

    E findById(I id);

    List<E> findAll();
}
