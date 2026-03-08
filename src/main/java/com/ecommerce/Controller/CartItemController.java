package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Cart_item;
import com.ecommerce.Service.CartItemService;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cis;

    public CartItemController(CartItemService cis) {
        this.cis = cis;
    }

    @PostMapping("/add-cart-item")
    public String addItem(@RequestBody Cart_item item) {
         cis.addItem(item);
         return "Cart item added successfully...";
    }

    // Get all cart items
    @GetMapping("get-cart-item")
    public List<Cart_item> getAllItems() {
        return cis.getAllItems();
    }

    // Get cart item by id
    @GetMapping("/{id}")
    public Cart_item getItem(@PathVariable Integer id) {
        return cis.getItem(id);
    }

    // Delete cart item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Integer id) {
        cis.deleteItem(id);
        return "Cart item deleted successfully";
    }
}