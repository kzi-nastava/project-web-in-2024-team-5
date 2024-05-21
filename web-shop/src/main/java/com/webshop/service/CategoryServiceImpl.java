package com.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Category;
import com.webshop.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategory(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public Category save(String name) {
        Category cat = new Category(name);
        return categoryRepository.save(cat);
    }

    public Category save(Category cat) {
        return categoryRepository.save(cat);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
