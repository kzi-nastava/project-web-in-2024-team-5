package com.webshop.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.dto.ProductResponse;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.Seller;
import com.webshop.model.TypeOfSale;
import com.webshop.service.CategoryServiceImpl;
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
    private CategoryServiceImpl categoryService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * Funkcionalnost 3.3
     * Kreira product. Ocekuje ovakav body(sva polja su obavezna):
     * {
     * "name":"przvd 1",
     * "description":"opis",
     * "imagePath":"/path/to/image.jpg",
     * "price":100,
     * "category":"Kitchen",
     * "typeOfSale":"FIXED_PRICE"
     * }
     * 
     * @param product
     * @param session
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ProductResponse> createProduct(@Validated @RequestBody ProductDto product,
            HttpSession session) {
        try {
            UserSession loggedUser = (UserSession) session.getAttribute("User");

            if (loggedUser == null || loggedUser.getRole().equals("buyer"))
                return ResponseEntity.badRequest().build();

            String cat = product.getCategory();
            if (cat != null && categoryService.findCategory(cat) == null)
                categoryService.save(cat);

            Seller seller = (Seller) userService.findById(loggedUser.getId());

            return ResponseEntity.ok(productService.createProduct(seller, product));

        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Funkcionalnost 3.5
     * Azurira informacije o proizvodu
     *
     * @param productDto
     * @param id
     * @param session
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id,
            HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null || loggedUser.getRole().equals("buyer"))
            return ResponseEntity.badRequest().build();

        Seller seller = (Seller) userService.findById(loggedUser.getId());

        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        if ((product.getSellerId() != seller.getId())
                || (product.getTypeOfSale() == TypeOfSale.AUCTION && !product.getOffers().isEmpty())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String cat = productDto.getCategory();
        if (cat != null && categoryService.findCategory(cat) == null)
            categoryService.save(cat);

        try {
            productDto = productService.updateProduct(product, productDto);
            return ResponseEntity.ok(productDto);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while saving the product", e);
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
    public List<BasicProductDto> getProducts(Pageable pageable) {
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
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
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
    public List<BasicProductDto> getFilteredProducts(
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
    public List<BasicProductDto> searchProducts(@RequestParam String search, Pageable pageable) {
        List<BasicProductDto> products = productService.searchProducts(search, pageable);

        return products;
    }

    /**
     * Deo Funkcionalnost 3.2 i 2.2
     * Vraca listu proizvoda koji pripadaju datom korisniku
     * 
     * @param session
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<BasicProductDto>> findProductsByUser(HttpSession session, @PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<BasicProductDto> productDtos = productService.findByUserId(id);

        if (!productDtos.isEmpty())
            return ResponseEntity.ok(productDtos);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<BasicProductDto>> findProductsByMe(HttpSession session) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<BasicProductDto> productDtos = productService.findByUserId(loggedUser.getId());

        if (!productDtos.isEmpty())
            return ResponseEntity.ok(productDtos);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<?> buyProduct(HttpSession session, @PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null || !loggedUser.getRole().equals("buyer")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Product product = productService.findById(id);

        if (product.getTypeOfSale() == TypeOfSale.FIXED_PRICE && !product.isSold()) {
            productService.buyProduct(product, loggedUser.getId());
            return ResponseEntity.ok(new ProductDto(product));

        } else if (product.getTypeOfSale() == TypeOfSale.AUCTION && !product.isSold()) {
            URI location = URI.create("/api/v1/offers");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", location.toString())
                    .body("Redirect to POST /api/v1/offers");
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/end-auction")
    public ResponseEntity<Product> endAuction(HttpSession session, @PathVariable Long id) {
        UserSession loggedUser = (UserSession) session.getAttribute("User");

        if (loggedUser == null || !loggedUser.getRole().equals("buyer")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Product product = productService.endAuction(id);

        return ResponseEntity.ok(product);
    }

}
