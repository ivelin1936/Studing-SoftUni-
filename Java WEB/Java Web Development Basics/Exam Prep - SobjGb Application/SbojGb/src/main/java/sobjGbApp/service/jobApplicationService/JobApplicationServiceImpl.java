package sobjGbApp.service.jobApplicationService;

import org.modelmapper.ModelMapper;
import sobjGbApp.domain.entities.JobApplication;
import sobjGbApp.domain.models.service.JobApplicationServiceModel;
import sobjGbApp.repository.jobApplicationRepo.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository,
                                     ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void add(JobApplicationServiceModel serviceModel) {
        JobApplication application = this.modelMapper.map(serviceModel, JobApplication.class);
        this.jobApplicationRepository.save(application);
    }

    @Override
    public List<JobApplicationServiceModel> findAll() {
        return this.jobApplicationRepository.findAll()
                .stream()
                .map(entity -> this.modelMapper.map(entity, JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationServiceModel findById(String id) {
        JobApplication jobApplication = this.jobApplicationRepository.findById(id);

        return jobApplication == null ? null
                : this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);
    }

    @Override
    public boolean remove(String id) {
        return this.jobApplicationRepository.deleteById(id);
    }
}
