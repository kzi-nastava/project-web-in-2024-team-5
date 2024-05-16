package com.webshop.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;
import com.webshop.service.CategoryService;
import com.webshop.service.ProductService;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Void> createProduct(@Validated @RequestBody Product product) {
        try {
            Product productResponse = productService.createProduct(product);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(productResponse.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Vraca listu BasicProductDto objekata koja se prikazuje na pocetnoj stranici,
     * limitiranu paginacijom
     * Primer:
     * localhost:8080/api/v1/products?size=5&page=2
     * Funkcionalnost 1.1 Pregled proizvoda
     */
    @GetMapping("")
    List<BasicProductDto> getProductsPageable(Pageable pageable) {
        return productService.findAll(pageable);
    }

    /**
     * Vraca celi product (stranica proizvoda)
     * Primer:
     * localhost:8080/api/v1/products/24
     * 
     * Funkcionalnost 1.1 Pregled proizvoda
     */
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getById(id);
        return (productDto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(productDto);
    }

    /**
     * Filtriranje proizvoda koriscenjem query parametara.
     * Primer:
     * localhost:8080/api/v1/products/filter?minPrice=1&maxPrice=10000&category=Kitchen&typeOfSale=AUCTIN
     * Funkcionalnost 1.2 filtriranje proizvoda
     *
     * @param minPrice
     * @param maxPrice
     * @param category
     * @param typeOfSale
     * @return List of productDtos
     */
    @GetMapping("/filter")
    List<BasicProductDto> getFilteredProducts(
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) TypeOfSale typeOfSale) {

        Category categoryObj = categoryService.findCategory(category);

        return productService.getFilteredProducts(minPrice, maxPrice, categoryObj, typeOfSale);
    }

    /**
     * Pretraga proizvoda po imenu i deskripciji.
     * Primer:
     * localhost:8080/api/v1/products/search?search=set&size=5&page=0
     * Funkcionalnost 1.2 pretraga proizvoda
     * 
     * @param search
     * @param pageable
     * @return
     */
    @GetMapping("/search")
    List<BasicProductDto> searchProducts(@RequestParam String search, Pageable pageable) {
        List<BasicProductDto> products = productService.searchProducts(search, pageable);

        return products;
    }

}
