package meTube.repository;

import meTube.domain.entities.Tube;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Tube findByName(String name);
}
