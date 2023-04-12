package com.softuni.mobilele.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.mobilele.domain.dtos.banding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilele.services.role.UserRoleService;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	private final UserRoleService userRoleService;

	public UserController(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	@GetMapping("/register")
	public ModelAndView getRegister(ModelAndView modelAndView) {
		List<UserRoleViewDto> roles = this.userRoleService.getAll();
		modelAndView.addObject("roles", roles);

		return super.view("auth-register", modelAndView);
	}

	@PostMapping("/register")
	public ModelAndView postRegister(@ModelAttribute UserRegisterFormDto userRegister) {
		
		return super.redirect("auth-login");
	}

}
