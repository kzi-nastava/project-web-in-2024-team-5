package com.webshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Buyer;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.Seller;
import com.webshop.model.TypeOfSale;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findById(Long id);

    Page<Product> findByCategoryAndTypeOfSale(Category category, TypeOfSale typeOfSale, Pageable pageable);

    List<Product> findAll();

    Product save(Product product);

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByTypeOfSale(TypeOfSale typeOfSale, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')) AND p.sold = false")
    Page<Product> findBySearchTerm(String search, Pageable pageable);

    List<Product> findAllBySellerAndBuyer(Seller sellerId, Buyer buyerId);

    List<Product> findAllByBuyerOrSeller(Buyer buyerId, Seller sellerId);

    Page<Product> findAllBySold(Pageable pageable, boolean isSold);

    List<Product> findAllBySeller(Seller seller);

    List<Product> findAllByBuyer(Buyer buyer);
}
