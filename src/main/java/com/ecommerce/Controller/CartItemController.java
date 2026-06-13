package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.CartItem;
import com.ecommerce.Repository.CartItemRepository;
 
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
 
    private final CartItemRepository cartItemRepository;
 
    public CartItemController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
 
    @PostMapping
    public CartItem addCartItem(@RequestBody CartItem cartItem) {
        CartItem saved = cartItemRepository.save(cartItem);
        return cartItemRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable int id) {
        return cartItemRepository.findById(id).orElse(null);
    }
 
    // GET items by cart
    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItemsByCart(@PathVariable int cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
 
    @PutMapping("/{id}")
    public CartItem updateCartItem(@PathVariable int id, @RequestBody CartItem updated) {
        CartItem item = cartItemRepository.findById(id).orElse(null);
        if (item == null) return null;
        item.setQuantity(updated.getQuantity());
        item.setFinal_price(updated.getFinal_price());
        return cartItemRepository.save(item);
    }
 
    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable int id) {
        cartItemRepository.deleteById(id);
        return "Cart item deleted successfully";
    }
}