package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Cart;
import com.ecommerce.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cs;

    public CartController(CartService cs) {
        this.cs = cs;
    }

    @PostMapping("add-cart")
    public Cart addCart(@RequestBody Cart cart) {
        return cs.addCart(cart);
    }
    
    @GetMapping("get-all-cart")
    public List<Cart> getAllCarts() {
        return cs.getAllCarts();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Integer id) {
        return cs.getCartById(id);
    }
}
