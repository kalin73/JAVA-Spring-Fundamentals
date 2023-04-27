package com.softuni.mobilele.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.mobilele.domain.dtos.binding.UserLoginFormDto;
import com.softuni.mobilele.domain.dtos.binding.UserRegisterFormDto;
import com.softuni.mobilele.domain.dtos.view.UserRoleViewDto;
import com.softuni.mobilele.services.role.UserRoleService;
import com.softuni.mobilele.services.user.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	private static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";
	private final UserRoleService userRoleService;
	private final UserService userService;

	public UserController(UserRoleService userRoleService, UserService userService) {
		this.userRoleService = userRoleService;
		this.userService = userService;
	}

	@GetMapping("/register")
	public String getRegister(Model model) {
		return "auth-register";
	}

	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute(name = "userRegisterForm") UserRegisterFormDto userRegisterForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("userRegisterForm", userRegisterForm)
					.addFlashAttribute(BINDING_RESULT_PATH + "userRegisterForm", bindingResult);

			return "redirect:/users/register";
		}

		this.userService.registerUser(userRegisterForm);

		return "redirect:/users/login";
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return super.view("auth-login");
	}

	@PostMapping("/login")
	public ModelAndView postLogin(@Valid @ModelAttribute(name = "userLoginForm") UserLoginFormDto userLoginForm,
			ModelAndView modelAndView, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return super.view("auth-login", modelAndView.addObject("userLoginForm", userLoginForm));
		}

		return super.redirect("/");
	}

	@PostMapping("/logout")
	public ModelAndView postLogout() {
		this.userService.logout();

		return super.redirect("/");
	}

	@ModelAttribute(name = "userRegisterForm")
	public UserRegisterFormDto initUserRegisterFormDto() {
		return new UserRegisterFormDto();
	}

	@ModelAttribute(name = "userLoginForm")
	public UserLoginFormDto initUserLoginFormDto() {
		return new UserLoginFormDto();
	}

	@ModelAttribute(name = "roles")
	public List<UserRoleViewDto> getAllRoles() {
		return this.userRoleService.getAll();

	}
}
