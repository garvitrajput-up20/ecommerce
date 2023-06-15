package com.lakshmi.exittestapi.body;

import com.lakshmi.exittestapi.entities.UsersEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

public class AuthResponse {
	
	/** The JWT token to be used for authentication. */
	private String jwt;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private UsersEntity usersEntity;
	
	

	public UsersEntity getUsersEntity() {
		return usersEntity;
	}

	public void setUsersEntity(UsersEntity usersEntity) {
		this.usersEntity = usersEntity;
	}

	public String getJwt() {
		return jwt;
	}

	private String message;

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
