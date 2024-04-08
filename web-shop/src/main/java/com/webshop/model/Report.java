package com.webshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports")
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String reason;

    @Column
    private LocalDateTime datePosted;

    @Enumerated(EnumType.STRING)
    @Column
    private ReportStatus status;

    @OneToOne
    private Buyer buyer;

    @OneToOne
    private Seller seller;

    @Enumerated(EnumType.STRING)
    @Column
    private Poster poster;
}
