package com.lakshmi.exittestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakshmi.exittestapi.body.AuthBody;
import com.lakshmi.exittestapi.body.AuthResponse;
import com.lakshmi.exittestapi.entities.UsersEntity;
import com.lakshmi.exittestapi.exceptions.UserExistsException;
import com.lakshmi.exittestapi.services.UserAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

	private UserAuthService userAuthService;
	
	public AuthenticationController(UserAuthService userAuthService) {
		this.userAuthService = userAuthService;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/registerUser")
	public ResponseEntity Registeration(@Valid @RequestBody UsersEntity usersEntity) {
		try {
			userAuthService.registerUser(usersEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Registration Success");
		} catch (UserExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUserByUsername(@Valid @RequestBody AuthBody authBody) {
		String jwt = userAuthService.loginUserByUsername(authBody);
		UsersEntity usersEntity = new UsersEntity();
		if (jwt == null) {
			AuthResponse errorResponse = new AuthResponse();
			errorResponse.setMessage("Invalid Credentials"); // Set the error message
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} else {
			AuthResponse response = new AuthResponse();
			response.setMessage("Login Success");
			response.setJwt(jwt);
			response.setUsersEntity(usersEntity);
			return ResponseEntity.ok(response); // Set the response status to 200 OK
		}
	}
	
	@GetMapping("/LoggedInUser")
	public UsersEntity getLoggedInUserData(@AuthenticationPrincipal UsersEntity usersEntity) {
		return usersEntity;
	}
	
}