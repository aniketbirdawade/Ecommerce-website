package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Cart;
import com.ecommerce.Repository.CartRepository;
import com.ecommerce.Repository.UserRepository;
 
@RestController
@RequestMapping("/api/carts")
public class CartController {
 
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
 
    public CartController(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }
 
    @PostMapping
    public ResponseEntity<?> addCart(@RequestBody Cart cart) {
        if (cart == null) {
            return ResponseEntity.badRequest().body("Cart cannot be null");
        }
        if (cart.getUser() == null || cart.getUser().getId() <= 0) {
            return ResponseEntity.badRequest().body("Valid user ID is required to create a cart");
        }
        
        // Validate user exists
        if (!userRepository.existsById(cart.getUser().getId())) {
            return ResponseEntity.badRequest().body("User with ID " + cart.getUser().getId() + " does not exist");
        }
        
        Cart saved = cartRepository.save(cart);
        return ResponseEntity.ok(cartRepository.findById(saved.getId()).get());
    }
 
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartRepository.findAll());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable int id) {
        if (!cartRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with ID " + id + " not found");
        }
        return ResponseEntity.ok(cartRepository.findById(id).get());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable int id) {
        if (!cartRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart with ID " + id + " not found");
        }
        cartRepository.deleteById(id);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}