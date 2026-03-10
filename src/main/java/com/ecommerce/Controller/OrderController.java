package com.ecommerce.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Order;
import com.ecommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create Order
    @PostMapping("/add-order")
    public Order createOrder(@RequestBody Order order) {
    	
    	System.out.print(order);
        return orderService.createOrder(order);
    }

    // Get Order by ID
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    // Get All Orders
    @GetMapping("/get-all-orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Update Order
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "Order deleted successfully";
    }
    
    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Integer userId) {
        return orderService.placeOrder(userId);
    }
    
    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable int id, @RequestBody String status) {
        return orderService.updateStatus(id, status);
    }
    
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable int userId) {
        return orderService.getOrdersByUser(userId);
    }
    
    
}
