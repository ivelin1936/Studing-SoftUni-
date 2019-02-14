package fdmcApp.service.impl;

import fdmcApp.domain.entities.Cat;
import fdmcApp.domain.models.service.CatServiceModel;
import fdmcApp.repository.interfaces.CatRepository;
import fdmcApp.service.interfaces.CatService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private CatRepository catRepository;
    private ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository,
                          ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public CatServiceModel save(CatServiceModel model) {
        Cat entity = this.modelMapper.map(model, Cat.class);
        Cat cat = this.catRepository.save(entity);
        return this.modelMapper.map(cat, CatServiceModel.class);
    }

    @Override
    public List<CatServiceModel> findAll() {
        List<Cat> allCats = this.catRepository.findAll();

        return allCats.stream()
                .map(entity -> this.modelMapper.map(entity, CatServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CatServiceModel findById(String id) {
        Cat cat = this.catRepository.findById(id);

        return cat == null ? null
                : this.modelMapper.map(cat, CatServiceModel.class);
    }
}
