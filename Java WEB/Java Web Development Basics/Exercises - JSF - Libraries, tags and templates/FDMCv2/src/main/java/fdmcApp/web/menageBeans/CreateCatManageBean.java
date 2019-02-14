package fdmcApp.web.menageBeans;

import fdmcApp.domain.models.binding.CatCreateBindingModel;
import fdmcApp.domain.models.service.CatServiceModel;
import fdmcApp.service.interfaces.CatService;
import fdmcApp.util.IValidator;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class CreateCatManageBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private CatCreateBindingModel bindingModel;

    private CatService catService;
    private ModelMapper modelMapper;
    private IValidator validator;

    public CreateCatManageBean() {
        this.bindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CreateCatManageBean(CatService catService,
                               ModelMapper modelMapper,
                               IValidator validator) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public CatCreateBindingModel getBindingModel() {
        return bindingModel;
    }

    public void setBindingModel(CatCreateBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }

    public void create() throws IOException {
        CatServiceModel serviceModel = this.modelMapper
                .map(this.bindingModel, CatServiceModel.class);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (validator.isValid(serviceModel)) {
            this.catService.save(serviceModel);
            context.redirect("/");
        } else {
            context.redirect(context.getRequestContextPath() + "/jsf/cat-create.xhtml");
        }
    }
}
