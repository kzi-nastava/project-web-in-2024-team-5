package com.webshop.dto;

import com.webshop.model.Product;

/**
 * ProductResponse - ima polja ProductDto i product id
 */
public class ProductResponse {

    private ProductDto productDto;

    private Long productId;

    public ProductResponse() {

    }

    public ProductResponse(ProductDto product, Long productId) {
        this.productDto = product;
        this.productId = productId;
    }

    public ProductResponse(Product product, Long productId) {
        this.productDto = new ProductDto(product);
        this.productId = productId;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long id) {
        this.productId = id;
    }

}
