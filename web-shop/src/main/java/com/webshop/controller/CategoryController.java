package com.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.Category;
import com.webshop.service.CategoryServiceImpl;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/api/v1/categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

}
