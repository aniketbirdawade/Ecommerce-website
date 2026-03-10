package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Product;
import com.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/products")
public class RecommendationController {

    private final ProductService ps;

    public RecommendationController(ProductService ps) {
        this.ps = ps;
    }

    @GetMapping("/recommendations/{productId}")
    public List<Product> getRecommendations(@PathVariable int productId) {
        return ps.getRecommendedProducts(productId);
    }
}