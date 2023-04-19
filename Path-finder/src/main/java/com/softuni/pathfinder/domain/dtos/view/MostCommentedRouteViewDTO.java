package com.softuni.pathfinder.domain.dtos.view;

import java.util.NoSuchElementException;

import com.softuni.pathfinder.domain.entities.Route;

public class MostCommentedRouteViewDTO {
	private String name;

	private String description;

	private String imageUrl;

	public MostCommentedRouteViewDTO() {

	}

	public MostCommentedRouteViewDTO(String name, String description, String imageUrl) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public MostCommentedRouteViewDTO setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public MostCommentedRouteViewDTO setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public MostCommentedRouteViewDTO setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public static MostCommentedRouteViewDTO fromRoute(Route route) {
		return new MostCommentedRouteViewDTO(route.getName(), route.getDescription(),
				route.getPictures()
						.stream()
						.findFirst()
						.orElseThrow(NoSuchElementException::new)
						.getUrl());
	}

}
