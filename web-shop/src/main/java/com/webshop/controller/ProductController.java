package com.webshop.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.Seller;
import com.webshop.model.TypeOfSale;
import com.webshop.service.CategoryService;
import com.webshop.service.ProductService;
import com.webshop.service.UserServiceImpl;
import com.webshop.session.UserSession;

import jakarta.servlet.http.HttpSession;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductDto product, HttpSession session) {
        try {
            UserSession loggedUser = (UserSession) session.getAttribute("User");

            if (loggedUser == null || loggedUser.getRole().equals("buyer"))
                return ResponseEntity.badRequest().build();

            String cat = product.getCategory();
            if (categoryService.findCategory(cat) == null)
                categoryService.save(cat);

            Seller seller = (Seller) userService.findById(loggedUser.getId());

            Product product2 = new Product(product);
            product2.setSellerId(seller.getId());
            product2.setSold(false);
            product2.setSaleStartDate(LocalDate.now());

            return ResponseEntity.ok(productService.createProduct(product2));

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
     * Funkcionalnost 1.3 pretraga proizvoda
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

    // TODO: 3.5. Ažuriranje informacija o proizvodu

}
