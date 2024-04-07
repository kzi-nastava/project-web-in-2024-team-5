package com.webshop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

enum typeOfSale {FIXED_PRICE, AUCTION}

/**
 * Product
 */
@Entity
public class Product {
    private String name;
    private String imagePath; // mozda, mozda i ne ?
    private String description;
    @ManyToOne
    private Category category;
    private Float price;
    private typeOfSale typeOfSale;
    private Date saleStartDate;
    @ManyToOne
    private Seller seller;
    private Boolean buyerReview;
    private Boolean sellerReview;
    private Boolean sold;
    @OneToMany
    private ArrayList<Offer> offers;

    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

