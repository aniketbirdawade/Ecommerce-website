package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Review;
import com.ecommerce.Repository.ReviewRepository;
 
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
 
    private final ReviewRepository reviewRepository;
 
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
 
    @PostMapping
    public Review addReview(@RequestBody Review review) {
        Review saved = reviewRepository.save(review);
        return reviewRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable int id) {
        return reviewRepository.findById(id).orElse(null);
    }
 
    // GET reviews by product
    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable int productId) {
        return reviewRepository.findByProductId(productId);
    }
 
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable int id) {
        reviewRepository.deleteById(id);
        return "Review deleted successfully";
    }
}