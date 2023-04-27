package com.softuni.thymeleafvalidation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.thymeleafvalidation.domain.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
