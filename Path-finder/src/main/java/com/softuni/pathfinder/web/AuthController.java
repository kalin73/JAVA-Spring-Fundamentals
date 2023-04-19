package com.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softuni.pathfinder.domain.dtos.binding.UserLoginForm;
import com.softuni.pathfinder.domain.dtos.binding.UserRegisterForm;
import com.softuni.pathfinder.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {
	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public ModelAndView getRegister(ModelAndView modelAndView, @ModelAttribute UserRegisterForm userRegisterInfo) {

		modelAndView.addObject("userRegisterInfo", userRegisterInfo);

		return super.view("register", modelAndView);
	}

	@PostMapping("/register")
	public ModelAndView postRegister(ModelAndView modelAndView, @Valid UserRegisterForm userRegisterInfo,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			redirectAttributes
					.addAttribute("org.springframework.validation.BindingResult.userRegisterInfo", bindingResult)
					.addFlashAttribute("userRegisterBindingModel", userRegisterInfo);

			return super.redirect("register");
		}

		this.userService.registerUser(userRegisterInfo);

		return super.redirect("login");
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		return super.view("login");
	}

	@PostMapping("/login")
	public ModelAndView postLogin(UserLoginForm userLoginInfo) {
		return this.userService.loginUser(userLoginInfo).isValid() ? super.redirect("/") : super.redirect("login");
	}

	@GetMapping("/logout")
	public ModelAndView logout() {
		this.userService.logout();

		return super.redirect("/");
	}

}