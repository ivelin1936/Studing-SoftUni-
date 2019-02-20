package sobjGbApp.domain.models.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {

    @NotNull
    @NotBlank(message = "Cannot be empty")
    private String username;

    @NotNull
    @NotBlank(message = "Cannot be empty")
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$", message = "Must be in format example@mail.gb")
    private String email;

    public UserRegisterBindingModel() {
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

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
