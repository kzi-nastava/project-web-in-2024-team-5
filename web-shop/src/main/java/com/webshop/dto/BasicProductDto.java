package com.webshop.dto;

import java.math.BigDecimal;

import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;

/**
 * Smanjena verzija proizvoda, za prikaz na glavnoj stranici
 */
public class BasicProductDto {

    private Long id;

    private String name;

    private String imagePath;

    private BigDecimal price;

    private TypeOfSale typeOfSale;

    public BasicProductDto() {
    }

    public BasicProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.price = product.getPrice();
        this.typeOfSale = product.getTypeOfSale();
    }

    public BasicProductDto(ProductDto product) {
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.price = product.getPrice();
    }

    public TypeOfSale getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(TypeOfSale typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
