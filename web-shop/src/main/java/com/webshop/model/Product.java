package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imagePath;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_name")
    private Category category;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column
    private TypeOfSale typeOfSale;

    @Column
    private LocalDateTime saleStartDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @Column
    private Boolean buyerReview;

    @Column
    private Boolean sellerReview;

    @Column
    private Boolean sold;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    public Long getId() {
        return id;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TypeOfSale getTypeOfSale() {
        return typeOfSale;
    }

    public void setTypeOfSale(TypeOfSale type) {
        this.typeOfSale = type;
    }

    public LocalDateTime getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDateTime saleStartDate) {
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

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

}
