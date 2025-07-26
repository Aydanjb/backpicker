package com.aydanjb.backpicker.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "A username is required")
    @Size(max = 30, message = "Username must be 30 characters or fewer")
    private String username;

    @NotBlank(message = "An email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "A password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    public RegisterRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
