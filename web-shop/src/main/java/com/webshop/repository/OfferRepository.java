package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
