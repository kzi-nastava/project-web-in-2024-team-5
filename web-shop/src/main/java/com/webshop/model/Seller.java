package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User {
    @OneToMany
    private ArrayList<Product> Products;

    @OneToMany
    private ArrayList<Review> Reviews;

    @Column
    private double averageRating;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}