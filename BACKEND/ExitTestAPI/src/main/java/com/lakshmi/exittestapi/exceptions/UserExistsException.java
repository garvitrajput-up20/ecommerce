package com.lakshmi.exittestapi.exceptions;

public class UserExistsException extends Exception{
	
	private static final long serialVersionUID = 232649334109383751L;

	public UserExistsException(String msg) {
		super(msg);
	}

}
