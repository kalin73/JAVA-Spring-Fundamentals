package com.softuni.pathfinder.services;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softuni.pathfinder.domain.dtos.binding.RouteAddForm;
import com.softuni.pathfinder.domain.dtos.model.CategoryModel;
import com.softuni.pathfinder.domain.dtos.model.RouteModel;
import com.softuni.pathfinder.domain.dtos.view.RoutePartialViewModel;
import com.softuni.pathfinder.domain.entities.Category;
import com.softuni.pathfinder.domain.entities.Route;
import com.softuni.pathfinder.domain.entities.User;
import com.softuni.pathfinder.domain.enums.CategoryName;
import com.softuni.pathfinder.helpers.LoggedUser;
import com.softuni.pathfinder.repositories.RouteRepository;

@Service
public class RouteService {
	private final RouteRepository routeRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final LoggedUser loggedUser;
	private final CategoryService categoryService;

	public RouteService(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService,
			LoggedUser loggedUser, CategoryService categoryService) {
		this.routeRepository = routeRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.loggedUser = loggedUser;
		this.categoryService = categoryService;
	}

	public RoutePartialViewModel getMostCommented() {
		final Route route = this.routeRepository.findMostCommented().orElseThrow(NoSuchElementException::new).get(0);

		return RoutePartialViewModel.fromRoute(route);

	}

	public List<RoutePartialViewModel> getAllRouts() {
		return this.routeRepository.findAll().stream().map(RoutePartialViewModel::fromRoute).toList();
	}

	public void addNewRoute(RouteAddForm routeAddForm) throws IOException {
		Route route = this.modelMapper.map(routeAddForm, Route.class);

		Set<Category> categories = routeAddForm.getCategories().stream().map(
				cm -> this.modelMapper.map(this.categoryService.findByName(CategoryName.valueOf(cm)), Category.class))
				.collect(Collectors.toSet());

		route.setAuthor(this.modelMapper.map(this.userService.findByUsername(loggedUser.getUsername()), User.class))
				.setCategories(categories).setGpxCoordinates(new String(routeAddForm.getGpxCoordinates().getBytes()));

		this.routeRepository.saveAndFlush(route);

	}

	@Transactional
	public RouteModel findById(Long id) {
		return this.modelMapper.map(this.routeRepository.findById(id).orElseThrow(NoSuchElementException::new),
				RouteModel.class);
	}

	public List<RoutePartialViewModel> findAllByCategoryName(String categoryName) {
		CategoryModel categoryModel = this.categoryService.findByName(CategoryName.valueOf(categoryName));

		return this.routeRepository.findAllByCategoriesContains(this.modelMapper.map(categoryModel, Category.class))
				.orElseThrow(NoSuchElementException::new).stream().map(RoutePartialViewModel::fromRoute).toList();
	}
}
