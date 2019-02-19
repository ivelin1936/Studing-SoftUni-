package pandaApp.Config;

import org.modelmapper.ModelMapper;
import pandaApp.utils.AppConstants;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory(AppConstants.PERSISTENCE_UNIT)
                .createEntityManager();
    }
}

