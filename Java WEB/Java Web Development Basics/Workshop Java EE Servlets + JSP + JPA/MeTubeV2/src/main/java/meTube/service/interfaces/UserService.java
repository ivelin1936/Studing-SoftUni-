package meTube.service.interfaces;

import meTube.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserServiceModel serviceModel);

    UserServiceModel findByName(String name);

    UserServiceModel findUser(String username, String password);
}
