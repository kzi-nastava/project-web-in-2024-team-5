package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal offerAmountMoney;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Buyer buyer;

    public Long getId() {
        return id;
    }

    public BigDecimal getOfferAmountMoney() {
        return offerAmountMoney;
    }

    public void setOfferAmountMoney(BigDecimal offerAmountMoney) {
        this.offerAmountMoney = offerAmountMoney;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

}
