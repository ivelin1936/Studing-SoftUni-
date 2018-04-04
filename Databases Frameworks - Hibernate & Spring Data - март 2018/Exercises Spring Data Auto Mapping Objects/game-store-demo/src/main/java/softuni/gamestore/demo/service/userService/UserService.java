package softuni.gamestore.demo.service.userService;

import softuni.gamestore.demo.model.dto.binding.UserLoginBindingModel;
import softuni.gamestore.demo.model.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.demo.model.dto.view.SuccessLoginUserViewModel;

public interface UserService {

    boolean register(UserRegisterBindingModel model);

    SuccessLoginUserViewModel login(UserLoginBindingModel model);
}
