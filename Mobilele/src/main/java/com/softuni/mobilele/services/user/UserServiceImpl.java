package com.softuni.mobilele.services.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.mobilele.domain.beans.LoggedUser;
import com.softuni.mobilele.domain.dtos.binding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.model.UserModel;
import com.softuni.mobilele.domain.entities.User;
import com.softuni.mobilele.repositories.UserRepository;
import com.softuni.mobilele.services.init.DataBaseInitService;
import com.softuni.mobilele.services.role.UserRoleService;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {
	private final UserRepository userRepository;
	private final UserRoleService userRoleService;
	private final ModelMapper modelMapper;
	private final LoggedUser loggedUser;

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleService userRoleService,
			LoggedUser loggedUser) {
		this.userRepository = userRepository;
		this.userRoleService = userRoleService;
		this.modelMapper = modelMapper;
		this.loggedUser = loggedUser;
	}

	@Override
	public void dbInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDbInit() {
		return this.userRepository.count() > 0;
	}

	@Override
	public UserModel registerUser(UserRegisterFormDto userRegister) {
		final UserModel userModel = modelMapper.map(userRegister, UserModel.class);

		userModel.setRole(!isDbInit() ? this.userRoleService.findAllRoles()
				: List.of(this.userRoleService.findRoleByName("USER")));

		User userToSave = this.modelMapper.map(userModel, User.class);

		return this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
	}

	@Override
	public void loginUser(UserLoginFormDto user) {
		UserModel loginCandidate = this.modelMapper.map(this.userRepository.findByUsername(user.getUsername()).get(),
				UserModel.class);

		this.loggedUser.setId(loginCandidate.getId()).setUsername(loginCandidate.getUsername())
				.setRoles(loginCandidate.getRole());

	}

	@Override
	public void logout() {
		this.loggedUser.clearFields();
	}

}
