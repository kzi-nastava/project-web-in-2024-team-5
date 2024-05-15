package com.webshop.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;
import com.webshop.model.User;

/**
 * Cela verzija proizvoda za detaljan prikaz
 */
public class ProductDto {

    private Long id;

    private String name;

    private String imagePath;

    private String description;

    private Category category;

    private BigDecimal price;

    private TypeOfSale typeOfSale;

    private LocalDateTime saleStartDate;

    private User seller;

    private User buyer;

    private Boolean sold;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.imagePath = product.getImagePath();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.typeOfSale = product.getTypeOfSale();
        this.saleStartDate = product.getSaleStartDate();
        this.seller = product.getSeller();
        this.buyer = product.getBuyer();
        this.sold = product.isSold();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
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

    public LocalDateTime getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDateTime saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

     public User getSeller() {
     return seller;
     }

     public void setSeller(User seller) {
     this.seller = seller;
     }

     public User getBuyer() {
     return buyer;
     }

     public void setBuyer(User buyer) {
     this.buyer = buyer;
     }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

}
