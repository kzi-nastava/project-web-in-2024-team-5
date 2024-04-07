package com.webshop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Buyer extends User {

    // @OneToMany(mappedBy = "Buyer", cascade = CascadeType.ALL)
    // private ArrayList<Product> Products;

    @OneToMany
    private List<Review> Reviews;

    @Column
    private double averageRating;
}