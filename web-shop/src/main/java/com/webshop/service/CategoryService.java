package com.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Category;
import com.webshop.repository.CategoryRepository;

/**
 * CategoryService
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategory(String name) {
        return categoryRepository.findByCategoryName(name);
    }

}
