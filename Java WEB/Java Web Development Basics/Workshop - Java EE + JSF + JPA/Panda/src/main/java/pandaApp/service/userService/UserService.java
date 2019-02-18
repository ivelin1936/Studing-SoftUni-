package pandaApp.service.userService;

import pandaApp.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    void register(UserServiceModel serviceModel);

    UserServiceModel login(UserServiceModel serviceModel);

    List<UserServiceModel> findAll();

    UserServiceModel findByUsername(String username);
}
