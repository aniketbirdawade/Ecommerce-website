package com.ecommerce.Repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Product;
 
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(int categoryId);
}