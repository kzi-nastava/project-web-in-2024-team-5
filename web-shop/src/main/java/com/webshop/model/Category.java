package com.webshop.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category implements Serializable {
    @Id
    private Long id;

    private String categoryName;

}
