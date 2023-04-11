package com.softuni.mobilele.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softuni.mobilele.domain.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, String> {

}
