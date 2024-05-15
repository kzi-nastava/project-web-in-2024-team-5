package com.webshop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.model.Category;
import com.webshop.model.TypeOfSale;
import com.webshop.service.ProductService;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // @Autowired
    // private CategoryService categoryService;

    /**
     * Vraca listu BasicProductDto objekata koja se prikazuje na pocetnoj stranici,
     * limitiranu paginacijom
     */
    @GetMapping("")
    List<BasicProductDto> getProductsPageable(Pageable pageable) {
        return productService.findAll(pageable);
    }

    /**
     * Vraca celi product (stranica proizvoda)
     * 
     */
    @GetMapping("/{id}")
    ProductDto getProduct(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/filter")
    List<BasicProductDto> getFilteredProducts(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) TypeOfSale typeOfSale) {

        // Category

        List<BasicProductDto> products = productService.getFilteredProducts(minPrice, maxPrice, category, typeOfSale);

        return products;
    }

}
