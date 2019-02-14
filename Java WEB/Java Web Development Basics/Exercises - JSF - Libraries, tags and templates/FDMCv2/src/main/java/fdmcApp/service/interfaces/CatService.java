package fdmcApp.service.interfaces;

import fdmcApp.domain.models.service.CatServiceModel;

import java.util.List;

public interface CatService {

    CatServiceModel save(CatServiceModel model);

    List<CatServiceModel> findAll();

    CatServiceModel findById(String id);
}
