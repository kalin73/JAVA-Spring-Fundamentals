package com.softuni.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilele.domain.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {

}
