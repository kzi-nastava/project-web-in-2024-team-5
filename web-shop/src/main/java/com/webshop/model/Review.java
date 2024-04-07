package com.webshop.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Review implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer score;

    @Column
    private String comment;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Seller seller;
}
