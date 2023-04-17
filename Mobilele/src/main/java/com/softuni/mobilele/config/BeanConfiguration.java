package com.softuni.mobilele.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.softuni.mobilele.domain.beans.LoggedUser;

@Configuration
public class BeanConfiguration {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	LoggedUser loggedUser() {
		return new LoggedUser();
	}
}
