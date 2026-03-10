package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Review;
import com.ecommerce.Service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService rs;

    public ReviewController(ReviewService rs) {
        this.rs = rs;
    }

    @PostMapping("add-review")
    public Review addReview(@RequestBody Review review) {
        return rs.addReview(review);
    }

    @GetMapping("/product/{productId}")
    public List<Review> getReviews(@PathVariable int productId) {
        return rs.getReviewsByProduct(productId);
    }
}
