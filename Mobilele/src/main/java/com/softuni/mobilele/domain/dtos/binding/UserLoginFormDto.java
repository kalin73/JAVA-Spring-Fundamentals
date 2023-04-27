package com.softuni.mobilele.domain.dtos.binding;

import com.softuni.mobilele.validations.userExists.ValidateLoginUser;

@ValidateLoginUser()
public class UserLoginFormDto {
	private String username;
	private String password;

	public UserLoginFormDto() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
