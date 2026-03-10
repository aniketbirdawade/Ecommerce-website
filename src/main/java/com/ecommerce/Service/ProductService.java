package com.ecommerce.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Product;
import com.ecommerce.Repository.ProductRepository;

@Service
public class ProductService  
{
	private final ProductRepository pr;
	
	public ProductService(ProductRepository pr) 
	{
		super();
		this.pr = pr;
	}

	public Product addProduct(Product product) 
	{
		
		 return pr.save(product);
	}
	
	public List<Product> getAllProducts(){
		
		return pr.findAll();
	 }
	 
	public Product getProductById(Integer id){
		
		return pr.findById(id).orElse(null);
	}

	public List<Product> searchByName(String name) {
		
		return pr.findByNameContaining(name);
	}

	public List<Product> getByCategory(Integer categoryId) {
		
		return pr.findByCategoryId(categoryId);
	}

	public List<Product> getByPriceRange(BigDecimal min, BigDecimal max) {
		
		return pr.findByPriceBetween(min, max);
	}
	
	public List<Product> getRecommendedProducts(int productId) {

        Product product = pr.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return pr.findByCategoryId(product.getCategory().getId());
    }
	
}