package com.softuni.mobilele.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilele.domain.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
	Optional<UserRole> findByRole(String role);
}
