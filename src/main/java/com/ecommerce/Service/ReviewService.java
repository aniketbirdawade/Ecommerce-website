package com.ecommerce.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.Review;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.ProductRepository;
import com.ecommerce.Repository.ReviewRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class ReviewService {

    private final ReviewRepository rr;
    private final UserRepository ur;
    private final ProductRepository pr;

    public ReviewService(ReviewRepository rr, UserRepository ur, ProductRepository pr) {
        this.rr = rr;
        this.ur = ur;
        this.pr = pr;
    }

    public Review addReview(Review review) {
    	User user = ur.findById(review.getUser().getId()).orElseThrow();
    	
    	Product product = pr.findById(review.getProduct().getId()).orElseThrow();
		
		review.setUser(user);
		review.setProduct(product);
    	
        return rr.save(review);
    }

    public List<Review> getReviewsByProduct(int productId) {
        return rr.findByProductId(productId);
    }
}
