package json.processing.service.userService;

import json.processing.model.dto.binding.UsersBindingModel;
import json.processing.model.entity.User;
import json.processing.repository.UserRepository;
import json.processing.util.modelMapper.DtoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void persist(UsersBindingModel userDto) {
        User user = DtoConvertUtil.convert(userDto, User.class);
        this.userRepository.save(user);
    }
}
