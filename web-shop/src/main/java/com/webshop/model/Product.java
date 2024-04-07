package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

enum typeOfSale {
    FIXED_PRICE,
    AUCTION
}

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imagePath;

    @Column
    private String description;

    // @ManyToOne(cascade = CascadeType.ALL)
    // private Category category;

    @Column
    private BigDecimal price;

    @Column
    private typeOfSale typeOfSale;

    @Temporal(TemporalType.DATE)
    private Date saleStartDate;

    // @ManyToOne
    // private Seller seller;

    @Column
    private Boolean buyerReview;

    @Column
    private Boolean sellerReview;

    @Column
    private Boolean sold;

    // @OneToMany
    // private ArrayList<Offer> offers;
}
