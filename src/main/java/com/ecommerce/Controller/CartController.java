package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Cart;
import com.ecommerce.Repository.CartRepository;
 
@RestController
@RequestMapping("/api/carts")
public class CartController {
 
    private final CartRepository cartRepository;
 
    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }
 
    @PostMapping
    public Cart addCart(@RequestBody Cart cart) {
        Cart saved = cartRepository.save(cart);
        return cartRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable int id) {
        return cartRepository.findById(id).orElse(null);
    }
 
    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable int id) {
        cartRepository.deleteById(id);
        return "Cart deleted successfully";
    }
}