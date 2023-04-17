package com.softuni.mobilele.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.model.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilele.services.role.UserRoleService;
import com.softuni.mobilele.services.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	private final UserRoleService userRoleService;
	private final UserService userService;

	public UserController(UserRoleService userRoleService, UserService userService) {
		this.userRoleService = userRoleService;
		this.userService = userService;
	}

	@GetMapping("/register")
	public ModelAndView getRegister(ModelAndView modelAndView) {
		List<UserRoleViewDto> roles = this.userRoleService.getAll();
		modelAndView.addObject("roles", roles);

		return super.view("auth-register", modelAndView);
	}

	@PostMapping("/register")
	public ModelAndView postRegister(UserRegisterFormDto userRegisterInfo) {
		this.userService.registerUser(userRegisterInfo);

		return super.redirect("/login");
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return super.view("auth-login");
	}

	@PostMapping("/login")
	public ModelAndView postLogin(UserLoginFormDto userLoginForm) {
		return this.userService.loginUser(userLoginForm).isValid() 
				? super.redirect("/") 
				: super.redirect("/users/login");
	}
	
	@PostMapping("/logout")
	public ModelAndView postLogout() {
		this.userService.logout();
		
		return super.redirect("/");
	}
}
