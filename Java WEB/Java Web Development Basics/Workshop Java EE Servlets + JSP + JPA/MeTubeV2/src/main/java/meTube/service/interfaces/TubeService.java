package meTube.service.interfaces;

import meTube.domain.models.service.TubeServiceModel;

public interface TubeService {

    void save(TubeServiceModel serviceModel);

    TubeServiceModel findById(String id);

    TubeServiceModel update(TubeServiceModel tubeServiceModel);
}
