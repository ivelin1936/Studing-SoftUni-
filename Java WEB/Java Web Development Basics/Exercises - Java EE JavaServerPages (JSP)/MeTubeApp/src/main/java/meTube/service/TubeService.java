package meTube.service;

import meTube.domain.models.serviceModels.TubeServiceModel;

import java.util.List;

public interface TubeService {

    void save(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> getAll();

    TubeServiceModel findByName(String name);
}
