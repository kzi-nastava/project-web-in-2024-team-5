package com.webshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webshop.model.Category;

/**
 * CategoryService
 */
@Service
public interface CategoryService {

    Category findCategory(String name);

    Category save(String name);

    Category save(Category cat);

    List<Category> findAll();
}
