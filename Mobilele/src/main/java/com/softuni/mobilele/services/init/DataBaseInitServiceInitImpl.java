package com.softuni.mobilele.services.init;

import org.springframework.stereotype.Service;

import com.softuni.mobilele.services.role.UserRoleService;

import jakarta.annotation.PostConstruct;

@Service
public class DataBaseInitServiceInitImpl implements DataBaseInitService {
	private final UserRoleService userRoleService;

	public DataBaseInitServiceInitImpl(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	@Override
	public void dbInit() {
		if (isDbInit()) {
			this.userRoleService.dbInit();
		}

	}

	@PostConstruct
	public void postConstruct() {
		dbInit();
	}

	@Override
	public boolean isDbInit() {
		return true;
	}

}
