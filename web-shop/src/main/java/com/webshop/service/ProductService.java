package com.webshop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return new ProductDto(productRepository.findById(id));
    }

    public List<BasicProductDto> getFilteredProducts(
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Category category,
            TypeOfSale typeOfSale) {
        // NOTE: OPTIMIZOVATI
        List<Product> products = productRepository.findByCategoryAndTypeOfSale(category, typeOfSale);
        List<BasicProductDto> productDtos = new ArrayList<>();

        for (Product prod : products) {
            BigDecimal cena = prod.getPrice();
            if (cena.compareTo(minPrice) != -1 && cena.compareTo(maxPrice) != 1) {
                productDtos.add(new BasicProductDto(prod));
            }
        }

        return productDtos;
    }

}
