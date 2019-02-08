package meTube.service.impl;

import meTube.domain.entities.User;
import meTube.domain.models.service.UserServiceModel;
import meTube.repository.impl.UserRepoImpl;
import meTube.service.interfaces.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepoImpl userRepo;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepoImpl userRepo,
                           ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel serviceModel) {
        User user = this.modelMapper.map(serviceModel, User.class);
        String sha256hex = DigestUtils.sha256Hex(serviceModel.getPassword());
        user.setPassword(sha256hex);
        this.userRepo.save(user);
    }

    @Override
    public UserServiceModel findByName(String name) {
        User user = this.userRepo.findByName(name);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUser(String username, String password) {
        String sha256hexPassword = DigestUtils.sha256Hex(password);
        User user = this.userRepo.findUser(username, sha256hexPassword);

        if (user == null) {
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
