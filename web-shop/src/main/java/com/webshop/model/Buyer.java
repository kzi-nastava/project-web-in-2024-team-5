package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Buyer extends User {
    @OneToMany
    private ArrayList<Product> Products;

    @OneToMany
    private ArrayList<Review> Reviews;

    @Column
    private double averageRating;
}