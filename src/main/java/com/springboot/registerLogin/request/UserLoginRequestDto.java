package com.springboot.registerLogin.request;

import jakarta.validation.constraints.NotNull;

public class UserLoginRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    // Getters and Setters
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
