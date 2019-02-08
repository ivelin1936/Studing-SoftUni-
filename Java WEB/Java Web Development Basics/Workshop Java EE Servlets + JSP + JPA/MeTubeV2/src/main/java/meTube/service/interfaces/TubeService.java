package meTube.service.interfaces;

import meTube.domain.models.service.TubeServiceModel;

import java.util.List;

public interface TubeService {

    void save(TubeServiceModel serviceModel);

    TubeServiceModel findById(String id);

    TubeServiceModel update(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> findAll();
}
