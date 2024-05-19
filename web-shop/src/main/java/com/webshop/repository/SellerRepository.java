package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
