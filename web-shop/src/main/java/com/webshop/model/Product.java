package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imagePath;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @Column
    private BigDecimal price;

    @Column
    private typeOfSale typeOfSale;

    @Temporal(TemporalType.DATE)
    private Date saleStartDate;

    // TODO:
    // @ManyToOne
    // private Seller seller;

    @Column
    private Boolean buyerReview;

    @Column
    private Boolean sellerReview;

    @Column
    private Boolean sold;

    // TODO:
    // @OneToMany
    // private ArrayList<Offer> offers;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public typeOfSale getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(typeOfSale typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Boolean getBuyerReview() {
        return buyerReview;
    }

    public void setBuyerReview(Boolean buyerReview) {
        this.buyerReview = buyerReview;
    }

    public Boolean getSellerReview() {
        return sellerReview;
    }

    public void setSellerReview(Boolean sellerReview) {
        this.sellerReview = sellerReview;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

}

enum typeOfSale {
    FIXED_PRICE,
    AUCTION
}
