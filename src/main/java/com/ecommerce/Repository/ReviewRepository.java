package com.ecommerce.Repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Review;
 
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(int productId);
}