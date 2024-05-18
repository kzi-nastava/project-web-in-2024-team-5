package com.webshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Product findById(Long id);

    List<Product> findByCategoryAndTypeOfSale(Category category, TypeOfSale typeOfSale);

    List<Product> findAll();

    Product save(Product product);

    List<Product> findByCategory(Category category);

    List<Product> findByTypeOfSale(TypeOfSale typeOfSale);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE %:search% OR LOWER(p.description) LIKE %:search%")
    Page<Product> findBySearchTerm(String search, Pageable pageable);

    List<Product> findAllBySellerIdAndBuyerId(Long sellerId, Long buyerId);
}
