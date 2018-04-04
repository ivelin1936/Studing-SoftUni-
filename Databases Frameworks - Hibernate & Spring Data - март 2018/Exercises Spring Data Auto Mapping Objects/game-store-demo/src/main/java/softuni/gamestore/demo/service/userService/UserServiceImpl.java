package softuni.gamestore.demo.service.userService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.gamestore.demo.model.dto.binding.UserLoginBindingModel;
import softuni.gamestore.demo.model.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.demo.model.dto.view.SuccessLoginUserViewModel;
import softuni.gamestore.demo.model.entity.User;
import softuni.gamestore.demo.repository.UserRepository;
import softuni.gamestore.demo.service.roleService.RoleService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    @Override
    public boolean register(UserRegisterBindingModel model) {
        User user = modelMapper.map(model, User.class);
        this.setUserRole(user);
        if (model.isPasswordMatch()) {
            user = this.userRepository.saveAndFlush(user);
        }
        return user.getId() != null;
    }

    @Override
    public SuccessLoginUserViewModel login(UserLoginBindingModel model) {
        User user = this.userRepository.findOneByEmail(model.getEmail());
        if (user != null) {
            if (user.getPassword().equals(model.getPassword())) {
                return this.modelMapper.map(user, SuccessLoginUserViewModel.class);
            }
        }
        return null;
    }

    private void setUserRole(User user) {
        if (this.userRepository.count() > 0) {
            user.getRole().add(this.roleService.getRoleByName(RoleService.Roles.USER));
        } else {
            user.getRole().add(this.roleService.getRoleByName(RoleService.Roles.ADMIN));
        }
    }
}
