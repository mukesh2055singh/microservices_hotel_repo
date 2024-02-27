package com.user.userService.entity;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
		super("User not found on server");
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
