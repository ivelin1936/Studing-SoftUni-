package softuni.gamestore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.gamestore.demo.model.dto.binding.UserLoginBindingModel;
import softuni.gamestore.demo.model.dto.binding.UserRegisterBindingModel;
import softuni.gamestore.demo.model.dto.view.SuccessLoginUserViewModel;
import softuni.gamestore.demo.service.gameService.GameService;
import softuni.gamestore.demo.service.roleService.RoleService;
import softuni.gamestore.demo.service.userService.UserService;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class CmdRunner implements org.springframework.boot.CommandLineRunner {

    private final GameService gameService;
    private final UserService userService;
    private final RoleService roleService;
    private final Set<Long> usersInSystem;

    @Autowired
    public CmdRunner(GameService gameService,
                     UserService userService,
                     RoleService roleService) {
        this.gameService = gameService;
        this.userService = userService;
        this.roleService = roleService;
        this.usersInSystem = new HashSet<>();
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input[] = reader.readLine().split("\\|");

        switch (input[0]) {
            case "RegisterUser":
                UserRegisterBindingModel registerModel = new UserRegisterBindingModel(input[1], input[2], input[3], input[4]);
                boolean isRegistered = this.userService.register(registerModel);

                if (isRegistered) {
                    System.out.println(registerModel.getFullName() + " was registered");
                }
                break;

            case "LoginUser":
                UserLoginBindingModel loginModel = new UserLoginBindingModel(input[1], input[2]);
                SuccessLoginUserViewModel successLoginUserViewModel = this.userService.login(loginModel);

                if (successLoginUserViewModel != null) {
                    this.usersInSystem.add(successLoginUserViewModel.getId());
                    System.out.println("Successfully logged in " + successLoginUserViewModel.getFullName());
                }
                //TODO
        }
    }
}
