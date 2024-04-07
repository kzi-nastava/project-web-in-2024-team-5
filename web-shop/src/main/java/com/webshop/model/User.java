package com.webshop.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

enum Role {
    buyer,
    seller,
    admin
}

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column
    private String profilePicture;

    @Column
    private String description;

    @Column
    private Role role;

    @Column
    private Boolean blocked;
}
