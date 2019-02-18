package pandaApp.repository.genericRepo;

import java.util.List;

public interface GenericRepository<E, I> {

    E save(E entity);

    E update(E entity);

    boolean delete(E entity);

    List<E> findAll();

    E findById(I id);
}
