package com.lakshmi.exittestapi.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthBody {

	private String username;


	@NotNull
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
