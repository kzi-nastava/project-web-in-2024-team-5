package com.webshop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Seller extends User {
    // @OneToMany
    // private List<Product> Products;

    @OneToMany(mappedBy = "seller")
    private List<Review> Reviews;

    @Column
    private double averageRating;
}
