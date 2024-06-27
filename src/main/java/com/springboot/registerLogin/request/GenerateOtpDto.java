package com.springboot.registerLogin.request;

import jakarta.validation.constraints.NotEmpty;

public class GenerateOtpDto {
	 @NotEmpty
	    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
