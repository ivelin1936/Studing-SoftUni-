package softuni.gamestore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.gamestore.demo.service.gameService.GameService;
import softuni.gamestore.demo.service.roleService.RoleService;
import softuni.gamestore.demo.service.userService.UserService;

import javax.transaction.Transactional;

@Component
@Transactional
public class CmdRunner implements org.springframework.boot.CommandLineRunner {

    private final GameService gameService;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CmdRunner(GameService gameService, UserService userService, RoleService roleService) {
        this.gameService = gameService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
