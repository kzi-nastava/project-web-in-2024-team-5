package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Seller implements User {
    @Column
    private ArrayList<Product> Products;

    @Column
    private ArrayList<Review> Reviews;

    @column
    private double averageRating;
}