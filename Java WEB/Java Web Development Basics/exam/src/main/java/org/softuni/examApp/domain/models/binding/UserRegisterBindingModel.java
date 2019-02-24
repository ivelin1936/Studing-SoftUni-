package org.softuni.examApp.domain.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegisterBindingModel {

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String username;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String password;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
    private String confirmPassword;

    @NotNull
    @NotEmpty(message = "Cannot be empty.")
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
