package com.ecommerce.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Product;
import com.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController 
{
	private final ProductService ps;

	public ProductController(ProductService ps) 
	{
		super();
		this.ps = ps;
	}
	
	@PostMapping("add-product")
	public Product addProduct(@RequestBody Product product)
	{
		
		return ps.addProduct(product);
		 
	}
	 
	 @GetMapping("get-all-product")
	 public List<Product> getAllProducts() 
	 {
		 return ps.getAllProducts();
	 }
	 
	 @GetMapping("/getById/{id}")
	 public Product getProductById(@PathVariable Integer id) {
	        return ps.getProductById(id);
	    }
	 
	 @GetMapping("/search-by-name")
	    public List<Product> searchByName(@RequestParam String name) {
	        return ps.searchByName(name);
	    }

	    @GetMapping("/category/{id}")
	    public List<Product> getByCategory(@PathVariable Integer id) {
	        return ps.getByCategory(id);
	    }

	    @GetMapping("/get-by-price")
	    public List<Product> getByPrice(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {

	        return ps.getByPriceRange(min, max);
	    }
	
		
}
