package users.system.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import users.system.entity.User;
import users.system.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class RunnerTerminal implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public RunnerTerminal(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        initUserData();
//        findAllByEmileProvider();
        removeInactiveUsers();
    }

    private void removeInactiveUsers() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter date (yyyy-MM-dd): ");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(reader.readLine());
        this.userService.removeAllUnActiveUsersAfterDate(date);
    }

    private void findAllByEmileProvider() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter email provider: ");
        String provider = reader.readLine();

        this.userService.findAllUsersByEmailProvider(provider).forEach(u -> {
            System.out.println(u.getUsername() + " " + u.getEmail());
        });
    }

    private void initUserData() {
        User user = new User();
        user.setUsername("gosho");
        user.setPassword("gsaA413?");
        user.setEmail("ggoev@cam.bg");
        user.setAge(18);
        user.setFirstName("Georgi");
        user.setLastName("Georgiev");

        this.userService.persist(user);
    }
}
