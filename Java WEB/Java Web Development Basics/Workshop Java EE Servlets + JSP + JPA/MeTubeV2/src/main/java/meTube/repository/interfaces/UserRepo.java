package meTube.repository.interfaces;

import meTube.domain.entities.User;
import meTube.domain.models.service.UserServiceModel;

public interface UserRepo extends GenericRepository<User, String> {

    User findByName(String name);

    User findUser(String username, String sha256hexPassword);
}
