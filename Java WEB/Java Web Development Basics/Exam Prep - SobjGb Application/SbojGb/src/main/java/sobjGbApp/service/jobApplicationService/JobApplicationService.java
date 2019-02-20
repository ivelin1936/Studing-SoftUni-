package sobjGbApp.service.jobApplicationService;

import sobjGbApp.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    void add(JobApplicationServiceModel serviceModel);

    List<JobApplicationServiceModel> findAll();

    JobApplicationServiceModel findById(String id);

    boolean remove(String id);
}
