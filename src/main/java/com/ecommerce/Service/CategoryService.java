package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Category;
import com.ecommerce.Repository.CategoryRepository;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}