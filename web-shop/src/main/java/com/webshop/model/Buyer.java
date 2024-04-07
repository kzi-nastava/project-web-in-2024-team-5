package com.webshop.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Buyer extends User {

    // @OneToMany(mappedBy = "Buyer", cascade = CascadeType.ALL)
    // private List<Product> Products;

    @OneToMany(mappedBy = "buyer")
    private List<Review> Reviews;

    @Column
    private double averageRating;
}
