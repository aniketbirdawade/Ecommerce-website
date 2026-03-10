package com.ecommerce.Repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
	List<Product> findByNameContaining(String name);

    List<Product> findByCategoryId(Integer categoryId);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);	


	
}
