package com.webshop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seller extends User {
    // @OneToMany
    // private List<Product> Products;

    @OneToMany(mappedBy = "seller")
    private List<Review> Reviews;

    @Column
    private double averageRating;
}
