package com.webshop.repository;

import java.util.List;

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

    List<Product> findByCategory(Category category);

    List<Product> findByTypeOfSale(TypeOfSale typeOfSale);

    Product save(Product product);

}
