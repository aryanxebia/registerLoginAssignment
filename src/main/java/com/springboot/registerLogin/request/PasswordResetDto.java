package com.springboot.registerLogin.request;

import jakarta.validation.constraints.NotEmpty;

public class PasswordResetDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
