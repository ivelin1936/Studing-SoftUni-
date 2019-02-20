package sobjGbApp.repository.jobApplicationRepo;

import sobjGbApp.domain.entities.JobApplication;
import sobjGbApp.repository.genericRepo.GenericRepository;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {

    boolean deleteById(String id);
}
