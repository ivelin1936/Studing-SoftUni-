package meTube.repository.interfaces;

import meTube.domain.entities.Tube;

public interface TubeRepo extends GenericRepository<Tube, String> {
    Tube update(Tube tube);
}
