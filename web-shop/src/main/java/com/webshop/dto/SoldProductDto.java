package com.webshop.dto;

import com.webshop.model.Product;

/**
 * SoldProductDto
 */
public class SoldProductDto {

    private ProductDto productDto;

    private Long buyerId;

    public SoldProductDto() {
    }

    public SoldProductDto(Product product) {
        this.productDto = new ProductDto(product);
        this.buyerId = product.getBuyerId();
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
}
