package com.webshop.model;

import java.io.Serializable;

import jakarta.persistence.Entity;

@Entity
public class Category implements Serializable {
    private String categoryName;

}
