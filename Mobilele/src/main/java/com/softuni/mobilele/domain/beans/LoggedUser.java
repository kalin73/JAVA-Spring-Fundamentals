package com.softuni.mobilele.domain.beans;

import java.util.List;

import com.softuni.mobilele.domain.dtos.model.UserRoleModel;

public class LoggedUser {
	private String id;
	private String username;
	private List<UserRoleModel> roles;

	public LoggedUser() {

	}

	public String getId() {
		return id;
	}

	public LoggedUser setId(String id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public LoggedUser setUsername(String username) {
		this.username = username;
		return this;
	}

	public List<UserRoleModel> getRoles() {
		return roles;
	}

	public LoggedUser setRoles(List<UserRoleModel> roles) {
		this.roles = roles;
		return this;
	}

	public void clearFields() {
		this.id = null;
		this.username = null;
		this.roles = null;
	}

}
