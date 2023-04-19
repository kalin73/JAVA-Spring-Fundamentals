package com.softuni.pathfinder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.pathfinder.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
