package pandaApp.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String username;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
