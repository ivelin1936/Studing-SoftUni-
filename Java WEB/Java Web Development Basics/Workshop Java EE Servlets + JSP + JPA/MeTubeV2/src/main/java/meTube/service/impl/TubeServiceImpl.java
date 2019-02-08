package meTube.service.impl;

import meTube.domain.entities.Tube;
import meTube.domain.models.service.TubeServiceModel;
import meTube.repository.interfaces.TubeRepo;
import meTube.service.interfaces.TubeService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepo tubeRepo;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepo tubeRepo,
                           ModelMapper modelMapper) {
        this.tubeRepo = tubeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(TubeServiceModel serviceModel) {
        Tube tube = this.modelMapper.map(serviceModel, Tube.class);
        this.tubeRepo.save(tube);
    }

    @Override
    public TubeServiceModel findById(String id) {
        Tube tube = this.tubeRepo.findById(id);
        return this.modelMapper.map(tube, TubeServiceModel.class);
    }

    @Override
    public TubeServiceModel update(TubeServiceModel tubeServiceModel) {
        long currentTubeViews = tubeServiceModel.getViews();
        tubeServiceModel.setViews(currentTubeViews + 1);

        Tube updatedTube = this.tubeRepo.update(this.modelMapper.map(tubeServiceModel, Tube.class));

        return this.modelMapper.map(updatedTube, TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> findAll() {
        List<TubeServiceModel> tubes = this.tubeRepo.findAll()
                .stream()
                .map(tube -> this.modelMapper.map(tube, TubeServiceModel.class))
                .collect(Collectors.toList());

        return tubes;
    }
}
