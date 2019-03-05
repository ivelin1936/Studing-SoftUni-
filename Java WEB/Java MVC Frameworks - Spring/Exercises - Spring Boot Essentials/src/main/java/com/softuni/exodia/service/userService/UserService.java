package com.softuni.exodia.service.userService;

import com.softuni.exodia.domain.models.service.UserServiceModel;

public interface UserService {

    void register(UserServiceModel serviceModel);

    UserServiceModel login(UserServiceModel serviceModel);
}
