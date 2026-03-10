package com.ecommerce.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.CartRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cr;
    private final UserRepository ur;
  
    public CartService(CartRepository cr, UserRepository ur) {
		super();
		this.cr = cr;
		this.ur = ur;
	}

	public Cart addCart(Cart cart) {
		User user = ur.findById(cart.getUser().getId()).orElseThrow();
		
		cart.setUser(user);
		
	    cart.setCreated_at(new Timestamp(System.currentTimeMillis()));

        cart.setUser(user);
        
        return cr.save(cart);
    }
    
    public List<Cart> getAllCarts() {
        return cr.findAll();
    }

    public Cart getCartById(Integer id) {
        return cr.findById(id).orElse(null);
    }
}
