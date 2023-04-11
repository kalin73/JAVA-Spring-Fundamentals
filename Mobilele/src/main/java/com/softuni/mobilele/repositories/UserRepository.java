package com.softuni.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilele.domain.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
