package com.ecommerce.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Category;
import com.ecommerce.Entity.HsnMaster;
import com.ecommerce.Entity.Product;
import com.ecommerce.Repository.CategoryRepository;
import com.ecommerce.Repository.HsnMasterRepository;
import com.ecommerce.Repository.ProductRepository;

@Service
public class ProductService  
{
	private final ProductRepository pr;
	private final CategoryRepository cr;
	private final HsnMasterRepository hmr;
	
	public ProductService(ProductRepository pr, CategoryRepository cr, HsnMasterRepository hmr) 
	{
		super();
		this.pr = pr;
		this.cr = cr;
		this.hmr = hmr;
	}

	public Product addProduct(Product product) {

	    Category category =
	            cr.findById(product.getCategory().getId())
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    product.setCategory(category);

	    // Auto assign HSN from category
	    product.setHsn(category.getHsn());

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