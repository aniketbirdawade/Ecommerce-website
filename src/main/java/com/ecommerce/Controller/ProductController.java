package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Product;
import com.ecommerce.Repository.ProductRepository;
 
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ReviewController reviewController;
 
    private final ProductRepository productRepository;
 
    public ProductController(ProductRepository productRepository, ReviewController reviewController) {
        this.productRepository = productRepository;
        this.reviewController = reviewController;
    }
 
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        Product saved = productRepository.save(product);
        return productRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).orElse(null);
    }
 
    // SEARCH by name
    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam String name) {

        return productRepository
                .findByNameContainingIgnoreCase(name);
    }
 
    // SEARCH by category
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
 
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product updated) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return null;
        product.setName(updated.getName());
        product.setPrice(updated.getPrice());
        product.setCategory(updated.getCategory());
        product.setHsn(updated.getHsn());
        return productRepository.save(product);
    }
 
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
    
}