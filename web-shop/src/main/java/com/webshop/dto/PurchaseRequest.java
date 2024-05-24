package com.webshop.dto;

import java.math.BigDecimal;

/**
 * PurchaseRequest
 */
public class PurchaseRequest {

    private BigDecimal offerAmountMoney;

    public BigDecimal getOfferAmountMoney() {
        return offerAmountMoney;
    }

    public void setOfferAmountMoney(BigDecimal offerAmountMoney) {
        this.offerAmountMoney = offerAmountMoney;
    }

}
