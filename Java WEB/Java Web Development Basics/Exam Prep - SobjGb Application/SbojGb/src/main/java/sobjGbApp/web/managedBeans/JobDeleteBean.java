package sobjGbApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import sobjGbApp.domain.models.service.JobApplicationServiceModel;
import sobjGbApp.domain.models.view.JobDeleteDeatilaViewModel;
import sobjGbApp.service.jobApplicationService.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class JobDeleteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private JobDeleteDeatilaViewModel viewModel;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobApplicationService jobApplicationService,
                         ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void initViewModel() {
        //Get passed param from the request
        String jobId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        JobApplicationServiceModel serviceModel = this.jobApplicationService.findById(jobId);

//        if (serviceModel == null) {
//            //LOG here...
//            //TODO -> render 404 Not found page
//            return;
//        }

        this.viewModel = this.modelMapper.map(serviceModel, JobDeleteDeatilaViewModel.class);
    }

    //Getter for job delete details view model
    public JobDeleteDeatilaViewModel getViewModel() {
        return this.viewModel;
    }
}
