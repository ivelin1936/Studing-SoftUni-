package org.softuni.examApp.service.userService;

import org.modelmapper.ModelMapper;
import org.softuni.examApp.domain.entities.User;
import org.softuni.examApp.domain.models.service.UserServiceModel;
import org.softuni.examApp.repository.userRepo.UserRepository;
import org.softuni.examApp.util.PasswordHasher;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordHasher passwordHasher;

    @Inject
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void register(UserServiceModel serviceModel) {
        User user = this.modelMapper.map(serviceModel, User.class);
        String encodedHash = passwordHasher.encodedHash(serviceModel.getPassword().toCharArray());
        user.setPassword(encodedHash);

        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel login(UserServiceModel serviceModel) {
        User user = userRepository.findByUsername(serviceModel.getUsername());

        char[] passwordCharArr = serviceModel.getPassword().toCharArray();
        if (user == null || !passwordHasher.verifyEncoded(user.getPassword(), passwordCharArr)) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAll() {
        return this.userRepository.findAll()
                .stream()
                .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
