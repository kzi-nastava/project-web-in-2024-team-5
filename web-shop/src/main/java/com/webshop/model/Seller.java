package com.webshop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seller extends User {
    // @OneToMany
    // private ArrayList<Product> Products;

    @OneToMany
    private List<Review> Reviews;

    @Column
    private double averageRating;
}