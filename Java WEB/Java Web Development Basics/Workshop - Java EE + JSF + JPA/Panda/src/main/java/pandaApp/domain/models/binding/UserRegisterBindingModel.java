package pandaApp.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 3, message = "Should be at least 3 characters long.")
    private String username;

    @NotNull
    @Size(min = 5, message = "Should be at least 5 characters long.")
    private String password;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String confirmPassword;

    @NotNull
    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$", message = "Invalid email format. Should be 'example@mail.com'")
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
