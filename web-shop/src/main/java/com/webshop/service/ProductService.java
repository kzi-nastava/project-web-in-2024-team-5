package com.webshop.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;
import com.webshop.repository.ProductRepository;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * nadje sve proizvode i konvertuje ih u basicProductDto
     * (objekti koji se prikazuju na glavnoj stranici)
     */
    public List<BasicProductDto> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> productList = products.getContent();
        List<BasicProductDto> basicProductDtos = new ArrayList<>();

        for (Product product : productList) {
            BasicProductDto basicProductDto = new BasicProductDto(product);
            basicProductDtos.add(basicProductDto);
        }

        return basicProductDtos;
    }

    public ProductDto getById(Long id) {
        Product prod = productRepository.findById(id);
        if (prod != null) {
            return new ProductDto(prod);
        }
        return null;
    }

    public List<BasicProductDto> getFilteredProducts(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Category category,
            TypeOfSale typeOfSale) {
        // TODO OPTIMIZOVATI, PAGINACIJA
        List<Product> products = initializeProducts(category, typeOfSale);
        List<BasicProductDto> productDtos = new ArrayList<>();

        minPrice = (minPrice == null) ? BigDecimal.ZERO : minPrice;

        maxPrice = (maxPrice == null) ? BigDecimal.valueOf(Double.MAX_VALUE) : maxPrice;

        for (Product prod : products) {
            BigDecimal cena = prod.getPrice();
            if (cena.compareTo(minPrice) != -1 && cena.compareTo(maxPrice) != 1) {
                productDtos.add(new BasicProductDto(prod));
            }
        }

        return productDtos;
    }

    public Product createProduct(@RequestBody Product product) {
        try {
            product.setSaleStartDate(LocalDate.now());
            product.setSold(false);
            return productRepository.save(product);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while saving the product", e);
        }
    }

    private List<Product> initializeProducts(Category category, TypeOfSale typeOfSale) {
        List<Product> products;

        if (category != null && typeOfSale != null) {
            products = productRepository.findByCategoryAndTypeOfSale(category, typeOfSale);
        } else if (category != null) {
            products = productRepository.findByCategory(category);
        } else if (typeOfSale != null) {
            products = productRepository.findByTypeOfSale(typeOfSale);
        } else {
            products = productRepository.findAll();
        }

        return products;
    }

}
