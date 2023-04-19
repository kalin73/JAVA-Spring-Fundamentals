package com.softuni.pathfinder.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.softuni.pathfinder.helpers.LoggedUser;

@Configuration
public class BeanConfiguration {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	@SessionScope
	LoggedUser loggedUser() {
		return new LoggedUser();
	}
}
