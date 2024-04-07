package com.webshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

enum typeOfSale {
    FIXED_PRICE,
    AUCTION
}

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private String imagePath;

    private String description;

    // @ManyToOne(cascade = CascadeType.ALL)
    // private Category category;

    private Float price;

    private typeOfSale typeOfSale;

    private Date saleStartDate;

    // @ManyToOne
    // private Seller seller;

    private Boolean buyerReview;

    private Boolean sellerReview;

    private Boolean sold;

    // @OneToMany
    // private ArrayList<Offer> offers;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

