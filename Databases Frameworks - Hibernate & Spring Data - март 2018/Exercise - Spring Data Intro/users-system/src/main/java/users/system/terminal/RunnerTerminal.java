package users.system.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import users.system.entity.User;
import users.system.service.UserService;

@Component
public class RunnerTerminal implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public RunnerTerminal(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        initUserData();
    }

    private void initUserData() {
        User user = new User();
        user.setUsername("gosho");
        user.setPassword("goS413?");
        user.setEmail("ggoshev@tam.bg");
        user.setAge(18);

        this.userService.persist(user);
    }


}
