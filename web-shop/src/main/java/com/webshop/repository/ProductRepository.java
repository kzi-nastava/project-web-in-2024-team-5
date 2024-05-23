package com.webshop.repository;

import java.util.List;

import com.webshop.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findById(Long id);

    List<Product> findByCategoryAndTypeOfSale(Category category, TypeOfSale typeOfSale);

    List<Product> findAll();

    Product save(Product product);

    List<Product> findByCategory(Category category);

    List<Product> findByTypeOfSale(TypeOfSale typeOfSale);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')) AND p.sold = false")
    Page<Product> findBySearchTerm(String search, Pageable pageable);

    List<Product> findAllBySellerAndBuyer(Seller sellerId, Buyer buyerId);

    List<Product> findAllByBuyerOrSeller(Buyer buyerId, Seller sellerId);

    Page<Product> findAllBySold(Pageable pageable, boolean isSold);

    List<Product> findAllBySeller(Seller seller);

    List<Product> findAllByBuyer(Buyer buyer);
}
