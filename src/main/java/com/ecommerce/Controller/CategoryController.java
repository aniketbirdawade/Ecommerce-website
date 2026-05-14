package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Category;
import com.ecommerce.Repository.CategoryRepository;
 
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
 
    private final CategoryRepository categoryRepository;
 
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
 
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        Category saved = categoryRepository.save(category);
        return categoryRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryRepository.findById(id).orElse(null);
    }
 
    // SEARCH by name
    @GetMapping("/search")
    public List<Category> searchCategories(@RequestParam String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }
 
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category updated) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) return null;
        category.setName(updated.getName());
        category.setDescription(updated.getDescription());
        category.setImgUrl(updated.getImgUrl());
        if (updated.getHsn() != null) {
            category.setHsn(updated.getHsn());
        }
        return categoryRepository.save(category);
    }
 
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryRepository.deleteById(id);
        return "Category deleted successfully";
    }
}