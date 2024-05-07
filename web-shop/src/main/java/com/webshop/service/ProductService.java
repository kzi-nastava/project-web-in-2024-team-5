package com.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.webshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * ProductService
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
}
