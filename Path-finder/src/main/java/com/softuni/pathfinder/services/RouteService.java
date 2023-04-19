package com.softuni.pathfinder.services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.softuni.pathfinder.domain.dtos.view.MostCommentedRouteViewDTO;
import com.softuni.pathfinder.domain.entities.Route;
import com.softuni.pathfinder.repositories.RouteRepository;

@Service
public class RouteService {
	private final RouteRepository routeRepository;

	public RouteService(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	public MostCommentedRouteViewDTO getMostCommented() {
		final Route route = this.routeRepository.findMostCommented().orElseThrow(NoSuchElementException::new).get(0);

		return MostCommentedRouteViewDTO.fromRoute(route);

	}
}
