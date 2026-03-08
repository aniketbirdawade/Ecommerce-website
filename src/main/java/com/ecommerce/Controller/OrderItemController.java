package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.Entity.Order_items;
import com.ecommerce.Service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService ois;

    public OrderItemController(OrderItemService ois) {
        this.ois = ois;
    }

    @PostMapping("/add-order-item")
    public String addOrderItem(@RequestBody Order_items item) {
        ois.addOrderItem(item);
        return "Order item added successfully!";
    }

    @GetMapping("/get-order-item")
    public List<Order_items> getAllItems() {
        return ois.getAllItems();
    }

    @GetMapping("/{id}")
    public Order_items getItem(@PathVariable Integer id) {
        return ois.getItem(id);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Integer id) {
     ois.deleteItem(id);
        return "Order item deleted successfully!";
    }
}