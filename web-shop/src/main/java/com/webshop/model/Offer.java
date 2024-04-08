package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "offers")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
