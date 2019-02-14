package fdmcApp.web.menageBeans;

import fdmcApp.domain.models.service.CatServiceModel;
import fdmcApp.domain.models.view.CatViewModel;
import fdmcApp.service.interfaces.CatService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatsAllManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CatViewModel> cats;

    private CatService catService;
    private ModelMapper modelMapper;

    public CatsAllManagedBean() {
    }

    @Inject
    public CatsAllManagedBean(CatService catService,
                              ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        List<CatServiceModel> allCatsServiceModel = this.catService.findAll();
        this.cats = allCatsServiceModel.stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, CatViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatViewModel> getCats() {
        return this.cats;
    }

    public void setCats(List<CatViewModel> cats) {
        this.cats = cats;
    }
}
