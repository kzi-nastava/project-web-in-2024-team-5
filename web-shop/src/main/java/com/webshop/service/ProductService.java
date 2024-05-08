package com.webshop.service;

import java.util.ArrayList;
import java.util.List;

import com.webshop.dto.BasicProductDto;
import com.webshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import com.webshop.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

}
