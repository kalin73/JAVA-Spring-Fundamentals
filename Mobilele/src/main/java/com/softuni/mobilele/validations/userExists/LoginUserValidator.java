package com.softuni.mobilele.validations.userExists;

import java.util.Optional;

import com.softuni.mobilele.domain.dtos.binding.UserLoginFormDto;
import com.softuni.mobilele.domain.entities.User;
import com.softuni.mobilele.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoginUserValidator implements ConstraintValidator<ValidateLoginUser, UserLoginFormDto> {
	private final UserRepository userRepository;

	public LoginUserValidator(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public boolean isValid(UserLoginFormDto userLoginForm, ConstraintValidatorContext context) {
		Optional<User> loginCandidate = this.userRepository.findByUsername(userLoginForm.getUsername());

		return loginCandidate.isPresent() && loginCandidate.get().getPassword().equals(userLoginForm.getPassword());

	}

}
