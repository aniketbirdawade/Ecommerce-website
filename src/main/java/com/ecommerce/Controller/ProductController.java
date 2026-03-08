package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Product;
import com.ecommerce.Service.ProductService;

@RestController
public class ProductController 
{
	private final ProductService ps;

	public ProductController(ProductService ps) 
	{
		super();
		this.ps = ps;
	}
	
	@PostMapping("add-product")
	public String addProduct(@RequestBody Product product)
	{
		
		 ps.addProduct(product);
		 return "Product Added...";
	}
	 @GetMapping("product")
	 public List<Product> getAllProducts() 
	 {
		 return ps.getAllProducts();
	 }
	 
	 @GetMapping("/{id}")
	 public Product getProductById(@PathVariable Integer id) {
	        return ps.getProductById(id);
	    }
	
		
}
