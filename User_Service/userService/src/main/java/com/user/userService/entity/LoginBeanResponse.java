package com.user.userService.entity;

import lombok.Builder;

public class LoginBeanResponse {
	String username;
	String jwtToken;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
