package com.softuni.exodia.service.userService;

import com.softuni.exodia.domain.entities.User;
import com.softuni.exodia.domain.models.service.UserServiceModel;
import com.softuni.exodia.repository.UserRepository;
import com.softuni.exodia.util.PasswordHasher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_INVALID_MODEL_MESSAGE = "Invalid user model!";
    private static final int DEFAULT_CONSTRAINT_VIOLATION_SIZE = 0;

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordHasher passwordHasher;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordHasher passwordHasher,
                           Validator validator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordHasher = passwordHasher;
        this.validator = validator;
    }

    @Override
    public void register(UserServiceModel serviceModel) {
        if (this.validator.validate(serviceModel).size() != DEFAULT_CONSTRAINT_VIOLATION_SIZE) {
            throw new IllegalArgumentException(DEFAULT_INVALID_MODEL_MESSAGE);
        }

        User userEntity = this.modelMapper.map(serviceModel, User.class);
        String encodedHash = passwordHasher.encodedHash(serviceModel.getPassword().toCharArray());
        userEntity.setPassword(encodedHash);

        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserServiceModel login(UserServiceModel serviceModel) {
        User user = this.userRepository.findByUsername(serviceModel.getUsername());

        char[] passwordCharArr = serviceModel.getPassword().toCharArray();
        if (user == null || !passwordHasher.verifyEncoded(user.getPassword(), passwordCharArr)) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
