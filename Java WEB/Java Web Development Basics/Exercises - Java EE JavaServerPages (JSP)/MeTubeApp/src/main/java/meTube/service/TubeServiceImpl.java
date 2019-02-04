package meTube.service;

import meTube.domain.entities.Tube;
import meTube.domain.models.serviceModels.TubeServiceModel;
import meTube.repository.TubeRepository;
import meTube.utils.ModelMapper;
import meTube.utils.ValidatorImpl;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidatorImpl validator;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository,
                           ModelMapper modelMapper,
                           ValidatorImpl validator) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void save(TubeServiceModel tubeServiceModel) {
        if (!this.validator.isValid(tubeServiceModel)) {
            throw new IllegalArgumentException();
        }

        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tube);
    }

    @Override
    public List<TubeServiceModel> getAll() {
        List<TubeServiceModel> tubes = this.tubeRepository.findAll()
                .stream()
                .map(t -> this.modelMapper.map(t, TubeServiceModel.class))
                .collect(Collectors.toList());

        return tubes;
    }

    @Override
    public TubeServiceModel findByName(String name) {
        Tube tube = this.tubeRepository.findByName(name);

        return tube == null ? null
                : this.modelMapper.map(tube, TubeServiceModel.class);
    }
}
