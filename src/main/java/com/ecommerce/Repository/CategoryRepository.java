package com.ecommerce.Repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Category;
 
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);
}