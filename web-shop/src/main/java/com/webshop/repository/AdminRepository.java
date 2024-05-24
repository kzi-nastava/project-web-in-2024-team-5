package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
