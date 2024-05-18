package com.webshop.dto;

import java.math.BigDecimal;

import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;

/**
 * Cela verzija proizvoda za detaljan prikaz
 */
public class ProductDto {

    private String name;

    private String description;

    private String imagePath;

    private BigDecimal price;

    private String category;

    private TypeOfSale typeOfSale;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.description = product.getDescription();
        this.category = product.getCategory().getCategoryName();
        this.price = product.getPrice();
        this.typeOfSale = product.getTypeOfSale();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TypeOfSale getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(TypeOfSale typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

}
