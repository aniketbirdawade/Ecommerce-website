package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Cart_item;
import com.ecommerce.Repository.CartItemRepository;
 
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
 
    private final CartItemRepository cartItemRepository;
 
    public CartItemController(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
 
    @PostMapping
    public Cart_item addCartItem(@RequestBody Cart_item cartItem) {
        Cart_item saved = cartItemRepository.save(cartItem);
        return cartItemRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Cart_item> getAllCartItems() {
        return cartItemRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Cart_item getCartItemById(@PathVariable int id) {
        return cartItemRepository.findById(id).orElse(null);
    }
 
    // GET items by cart
    @GetMapping("/cart/{cartId}")
    public List<Cart_item> getItemsByCart(@PathVariable int cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
 
    @PutMapping("/{id}")
    public Cart_item updateCartItem(@PathVariable int id, @RequestBody Cart_item updated) {
        Cart_item item = cartItemRepository.findById(id).orElse(null);
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