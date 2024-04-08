package com.webshop.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private BigDecimal offerAmountMoney;

    @ManyToOne
    private Buyer buyer;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
