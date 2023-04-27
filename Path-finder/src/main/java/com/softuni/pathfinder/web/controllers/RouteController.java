package com.softuni.pathfinder.web.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.softuni.pathfinder.domain.dtos.binding.RouteAddForm;
import com.softuni.pathfinder.domain.dtos.view.RoutePartialViewModel;
import com.softuni.pathfinder.services.RouteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/routes")
public class RouteController extends BaseController {
	private final RouteService routeService;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping
	public ModelAndView getAllRoutes(ModelAndView modelAndView) {
		return super.view("routes", modelAndView.addObject("routes", this.routeService.getAllRouts()));
	}

	@GetMapping("/add")
	public ModelAndView addRoute() {
		return super.view("add-route");
	}

	@PostMapping("/add")
	public ModelAndView addRoute(ModelAndView modelAndView, @Valid RouteAddForm routeAddForm,
			BindingResult bindingResult) throws IOException {

		if (bindingResult.hasErrors()) {
			return super.view("add-route", modelAndView.addObject(routeAddForm));

		}
		this.routeService.addNewRoute(routeAddForm);

		return super.redirect("/routes");
	}

	@GetMapping("/{id}")
	public ModelAndView getRouteById(@PathVariable Long id, ModelAndView modelAndView) {
		return super.view("route-details", modelAndView.addObject("route", this.routeService.findById(id)));

	}

	@GetMapping("/byType/{type}")
	public ModelAndView getAllByType(@PathVariable String type, ModelAndView modelAndView) {
		List<RoutePartialViewModel> routes = this.routeService.findAllByCategoryName(type.toUpperCase());
		modelAndView.addObject("routes", routes);

		return super.view("route-by-type", modelAndView);

	}

	// ModelAttributes

	@ModelAttribute(name = "routeAddForm")
	public RouteAddForm initAddRoute() {
		return new RouteAddForm();
	}

}
