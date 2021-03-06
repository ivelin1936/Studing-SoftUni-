package meTube.repository.interfaces;

import java.util.List;

public interface GenericRepository<Entity, Id> {

    Entity save(Entity entity);

    Entity findById(Id id);

    List<Entity> findAll();
}
