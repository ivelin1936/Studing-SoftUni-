package sobjGbApp.web.managedBeans;

import org.modelmapper.ModelMapper;
import sobjGbApp.domain.entities.enumerations.Sector;
import sobjGbApp.domain.models.binding.JobBindingModel;
import sobjGbApp.domain.models.service.JobApplicationServiceModel;
import sobjGbApp.service.jobApplicationService.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Named
@RequestScoped
public class AddJobBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private JobBindingModel bindingModel;
    private List<String> jobSectors;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public AddJobBean() {
    }

    @Inject
    public AddJobBean(JobApplicationService jobApplicationService,
                      ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
        this.bindingModel = new JobBindingModel();
    }

    @PostConstruct
    private void initJobSectors() {
        this.jobSectors = Stream.of(Sector.values())
                .map(Sector::name)
                .collect(Collectors.toList());
    }

    //Getter for binding model
    public JobBindingModel getBindingModel() {
        return this.bindingModel;
    }

    //Setter for binding model
    public void setBindingModel(JobBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }

    //Getter for job sectors
    public List<String> getJobSectors() {
        return this.jobSectors;
    }

    public void add() throws IOException {
        JobApplicationServiceModel jobApplicationServiceModel =
                this.modelMapper.map(this.bindingModel, JobApplicationServiceModel.class);

        this.jobApplicationService.add(jobApplicationServiceModel);

        //Redirect to home page
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/view/home.xhtml");
    }
}
