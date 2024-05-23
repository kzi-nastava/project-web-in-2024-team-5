package com.webshop.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webshop.dto.BasicProductDto;
import com.webshop.dto.ProductDto;
import com.webshop.dto.ProductResponse;
import com.webshop.model.Buyer;
import com.webshop.model.Category;
import com.webshop.model.Product;
import com.webshop.model.Seller;
import com.webshop.model.TypeOfSale;
import com.webshop.repository.BuyerRepository;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.SellerRepository;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SellerRepository sellerRepository;

    /**
     * nadje sve proizvode i konvertuje ih u basicProductDto
     * (objekti koji se prikazuju na glavnoj stranici)
     */
    public List<BasicProductDto> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAllBySold(pageable, false);
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

    public ProductResponse createProduct(Seller seller, ProductDto product) {
        try {
            Product product2 = new Product(product);
            product2.setSellerId(seller);
            product2.setSold(false);
            product2.setSaleStartDate(LocalDate.now());

            // seller.getProducts().add(product2);
            // seller = sellerRepository.save(seller);

            product2 = productRepository.save(product2);
            return new ProductResponse(product2, product2.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while saving the product", e);
        }
    }

    public List<BasicProductDto> searchProducts(String search, Pageable pageable) {
        search = search.trim().toLowerCase();

        List<BasicProductDto> productDtos = new ArrayList<>();

        List<Product> products = productRepository.findBySearchTerm(search, pageable).getContent();

        for (Product prod : products) {
            productDtos.add(new BasicProductDto(prod));
        }

        return productDtos;
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDto updateProduct(Product product, ProductDto productDto) {
        updateAndCheck(product, productDto);

        try {
            productRepository.save(product);
            return new ProductDto(product);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while saving the product", e);
        }

    }

    public List<BasicProductDto> findByUserId(Long id) {
        List<Product> products;
        List<BasicProductDto> bDtos = new ArrayList<>();
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
            products = seller.get().getProducts();
            for (Product prod : products) {
                if (!prod.isSold())
                    bDtos.add(new BasicProductDto(prod));
            }
        } else {
            Buyer buyer = buyerRepository.findById(id).get();
            products = buyer.getProducts();
            for (Product prod : products) {
                bDtos.add(new BasicProductDto(prod));
            }

        }

        return bDtos;
    }

    public Product buyProduct(Product product, Long buyerId) {
        Buyer buyer = buyerRepository.findById(buyerId).get();

        product.setBuyerId(buyer);
        product.setSold(true);

        buyer.getProducts().add(product);
        buyer = buyerRepository.save(buyer);

        Seller seller = product.getSeller();
        seller.getProducts().remove(product);
        sellerRepository.save(seller);

        emailService.sendEmail(buyer.getEmail(), "Your new product", "You won the auction!");

        emailService.sendEmail(seller.getEmail(), "Your product", "You sold your product");

        return product;
    }

    public Product endAuction(Long id) {
        Product product = productRepository.findById(id);

        product.setSold(true);

        Buyer buyer = buyerRepository.findById(product.getBuyerId()).get();

        productRepository.save(product);

        emailService.sendEmail(buyer.getEmail(), "Your auction", "You won the auction!");

        return product;
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

    private void updateAndCheck(Product product, ProductDto productDto) {
        if (productDto.getName() != null)
            product.setName(productDto.getName());

        if (productDto.getDescription() != null)
            product.setDescription(productDto.getDescription());

        if (productDto.getPrice() != null)
            product.setPrice(productDto.getPrice());

        if (productDto.getCategory() != null)
            product.setCategory(product.getCategory());

        if (productDto.getImagePath() != null)
            product.setImagePath(productDto.getImagePath());
    }

}
