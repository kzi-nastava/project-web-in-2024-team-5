package com.webshop.dto;

import java.math.BigDecimal;

/**
 * PurchaseRequest
 */
public class PurchaseRequest {

    public BigDecimal getOfferAmountMoney() {
        return offerAmountMoney;
    }

    public void setOfferAmountMoney(BigDecimal offerAmountMoney) {
        this.offerAmountMoney = offerAmountMoney;
    }

    private BigDecimal offerAmountMoney;

}
