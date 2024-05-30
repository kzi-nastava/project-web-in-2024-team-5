package com.webshop.dto;

import java.math.BigDecimal;

public class OfferDto {

    private BigDecimal price;

    private Long productId;

    public BigDecimal getPrice() {
        return price;
    }
    public Long getProductId() {
        return productId;
    }
}
