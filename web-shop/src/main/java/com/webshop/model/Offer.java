package com.webshop.model;

import java.math.BigDecimal;
import jakarta.persistence.*;
@Entity
public class Offer {
    private BigDecimal offerAmountMoney;
    @ManyToOne
    private Buyer buyer;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
