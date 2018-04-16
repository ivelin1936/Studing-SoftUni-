package json.processing.service.userService;

import json.processing.model.dto.binding.UsersBindingModel;
import json.processing.model.dto.view.UserViewModel;
import json.processing.model.dto.view.UserWithSoldItemViewModel;
import json.processing.model.dto.view.usersAndProductsQuery4.UsersViewModelWrapper;
import json.processing.model.entity.User;

import java.util.List;


public interface UserService {

    void persist(UsersBindingModel userDto);

    UserViewModel getUserById(Long sellerID);

    UsersViewModelWrapper getAllUsersWithSoldProduct();
}
