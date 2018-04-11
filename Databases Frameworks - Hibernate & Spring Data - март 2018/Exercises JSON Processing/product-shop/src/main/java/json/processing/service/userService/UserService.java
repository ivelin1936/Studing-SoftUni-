package json.processing.service.userService;

import json.processing.model.dto.binding.UsersBindingModel;


public interface UserService {

    void persist(UsersBindingModel userDto);
}
