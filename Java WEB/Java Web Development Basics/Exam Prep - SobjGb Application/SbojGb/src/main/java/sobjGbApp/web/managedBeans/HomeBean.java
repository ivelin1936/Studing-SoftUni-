package sobjGbApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import sobjGbApp.domain.models.view.JobHomeViewModel;
import sobjGbApp.service.jobApplicationService.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<JobHomeViewModel> jobsViewModels;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(JobApplicationService jobApplicationService,
                    ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initJobs() {
        this.jobsViewModels = this.jobApplicationService.findAll()
                .stream()
                .map(serviceModel -> {
                    JobHomeViewModel viewModel = this.modelMapper.map(serviceModel, JobHomeViewModel.class);
                    String imgFileName = serviceModel.getSector().name().toLowerCase() + ".jpg";
                    viewModel.setSectorImg(imgFileName);

                    return viewModel;
                }).collect(Collectors.toList());
    }

    //Getter for job view models
    public List<JobHomeViewModel> getJobsViewModels() {
        return this.jobsViewModels;
    }

    public void setJobsViewModels(List<JobHomeViewModel> jobsViewModels) {
        this.jobsViewModels = jobsViewModels;
    }
}
