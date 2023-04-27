package com.softuni.mobilele.services.user;

import com.softuni.mobilele.domain.dtos.binding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.model.UserModel;

public interface UserService {
	UserModel registerUser(UserRegisterFormDto userRegister);

	void loginUser(UserLoginFormDto user);

	void logout();

}
