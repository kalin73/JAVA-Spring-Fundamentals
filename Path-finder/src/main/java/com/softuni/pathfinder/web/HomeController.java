package com.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.pathfinder.domain.dtos.view.MostCommentedRouteViewDTO;
import com.softuni.pathfinder.services.RouteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
	private final RouteService routeService;
	private static final String USERNAME_SESSION_KEY = "username";

	public HomeController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping("/")
	public ModelAndView getHome(ModelAndView modelAndView, HttpSession httpSession) {
		String username = httpSession.getAttribute(USERNAME_SESSION_KEY) != null
				? httpSession.getAttribute(USERNAME_SESSION_KEY).toString()
				: "";

		final MostCommentedRouteViewDTO mostCommented = routeService.getMostCommented();

		modelAndView.addObject("mostCommented", mostCommented);
		modelAndView.addObject(USERNAME_SESSION_KEY, username);

		return super.view("index", modelAndView);
	}
}
