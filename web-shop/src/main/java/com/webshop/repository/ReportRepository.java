package com.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
