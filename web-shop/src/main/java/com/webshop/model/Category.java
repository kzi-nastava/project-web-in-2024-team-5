package com.webshop.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category implements Serializable {
    @Id
    @Column(unique = true)
    private String categoryName;
}
