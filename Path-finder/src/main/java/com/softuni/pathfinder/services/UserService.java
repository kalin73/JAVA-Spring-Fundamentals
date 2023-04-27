package com.softuni.pathfinder.services;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.softuni.pathfinder.domain.dtos.binding.RoleChangeForm;
import com.softuni.pathfinder.domain.dtos.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dtos.binding.UserRegisterForm;
import com.softuni.pathfinder.domain.dtos.model.UserModel;
import com.softuni.pathfinder.domain.dtos.view.UserProfileModel;
import com.softuni.pathfinder.domain.entities.Role;
import com.softuni.pathfinder.domain.entities.User;
import com.softuni.pathfinder.domain.enums.Level;
import com.softuni.pathfinder.domain.enums.RoleName;
import com.softuni.pathfinder.helpers.LoggedUser;
import com.softuni.pathfinder.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final ModelMapper modelMapper;
	private final LoggedUser loggedUser;

	public UserService(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService,
			LoggedUser loggedUser) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.modelMapper = modelMapper;
		this.loggedUser = loggedUser;
	}

	@Transactional
	public void registerUser(UserRegisterForm userRegister) {
		final UserModel userModel = modelMapper.map(userRegister, UserModel.class);

		userModel.setRoles(this.userRepository.count() == 0 ? this.roleService.findAllRoles()
				: Set.of(this.roleService.findRoleByName("USER")));

		User userToSave = this.modelMapper.map(userModel, User.class).setLevel(Level.BEGINNER);

		this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
	}

	@Transactional
	public UserModel loginUser(UserLoginForm user) {
		Optional<User> loginCandidate = this.userRepository.findByUsername(user.getUsername());

		UserModel userConfirmation = loginCandidate.isPresent()
				&& loginCandidate.get().getPassword().equals(user.getPassword())
						? this.modelMapper.map(loginCandidate.get(), UserModel.class)
						: new UserModel();

		if (userConfirmation.isValid()) {
			this.loggedUser.setId(userConfirmation.getId()).setUsername(userConfirmation.getUsername())
					.setRoles(userConfirmation.getRoles());
		}

		return userConfirmation;
	}

	public void logout() {
		this.loggedUser.clearFields();
	}

	public Set<RoleName> changeUserPermissions(Long userId, RoleChangeForm roleModelToSet,
			boolean shouldReplaceCurrentRoles) {
		final User user = this.userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

		final Role roleToSave = this.modelMapper.map(this.roleService.findRoleByName(roleModelToSet.getRoleName()),
				Role.class);

		if (shouldReplaceCurrentRoles) {
			user.setRoles(Set.of(roleToSave));

		} else {
			user.getRoles().add(roleToSave);

		}
		this.userRepository.saveAndFlush(user);

		return user.getRoles().stream().map(Role::getRole).collect(Collectors.toSet());
	}

	public UserProfileModel getLoggedUserProfile() {
		User user = this.userRepository.findById(this.loggedUser.getId()).get();

		return modelMapper.map(user, UserProfileModel.class);
	}

	public UserModel findByUsername(String username) {
		return modelMapper.map(this.userRepository.findByUsername(username), UserModel.class);
	}

}
