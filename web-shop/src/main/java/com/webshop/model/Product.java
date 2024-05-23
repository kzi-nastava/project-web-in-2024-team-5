package com.webshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.webshop.dto.ProductDto;
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

    @Column(nullable = false)
    private String name;

    @Column
    private String imagePath;

    @Column(length = 1000, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "category_name")
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfSale typeOfSale;

    @ManyToOne
    @JoinColumn()
    private Buyer buyer;

    @ManyToOne
    @JoinColumn
    private Seller seller;

    @Column
    private LocalDate saleStartDate;

    @Column
    private Boolean buyerReview;

    @Column
    private Boolean sellerReview;

    @Column
    private Boolean sold;

    public Product() {
    }

    public Product(ProductDto productDto) {
        this.name = productDto.getName();
        this.imagePath = productDto.getImagePath();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.typeOfSale = productDto.getTypeOfSale();
        this.category = new Category(productDto.getCategory());
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public LocalDate getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDate saleStartDate) {
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

    public Boolean isSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public Long getSellerId() {
        return seller.getId();
    }

    public void setSellerId(Seller seller) {
        this.seller = seller;
    }

    public Long getBuyerId() {
        return buyer.getId();
    }

    public void setBuyerId(Buyer buyer) {
        this.buyer = buyer;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Boolean getSold() {
        return sold;
    }

}
