package com.ecommerce.Service;

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

	public void addProduct(Product product) 
	{
		
		 pr.save(product);
	}
	
	 public List<Product> getAllProducts()
	 {
		 return pr.findAll();
	 }
	 
	 public Product getProductById(Integer id)
	 {
		 return pr.findById(id).orElse(null);
	}

	
	
}
