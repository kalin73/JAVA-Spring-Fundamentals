package com.softuni.mobilele.services.user;

import org.springframework.stereotype.Service;

import com.softuni.mobilele.repositories.UserRepository;
import com.softuni.mobilele.services.init.DataBaseInitService;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void dbInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDbInit() {
		return this.userRepository.count() > 0;
	}

}
