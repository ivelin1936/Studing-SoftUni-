package softuni.gamestore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.gamestore.demo.command.CommandFactory;
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

    private final CommandFactory cf;
    private final GameService gameService;
    private final UserService userService;
    private final RoleService roleService;
    private final Set<Long> usersInSystem;

    @Autowired
    public CmdRunner(CommandFactory cf,
                     GameService gameService,
                     UserService userService,
                     RoleService roleService) {
        this.cf = new CommandFactory();
        this.gameService = gameService;
        this.userService = userService;
        this.roleService = roleService;
        this.usersInSystem = new HashSet<>();
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final CommandFactory cf = CommandFactory.init(gameService, userService, roleService);

        cf.listCommands();
        String[] commandTokens = reader.readLine().split("\\|");

        while (!commandTokens[0].equalsIgnoreCase("Exit")) {
            cf.listCommands();
            cf.executeCommand(commandTokens);

            commandTokens = reader.readLine().split("\\|");
        }
    }
}
