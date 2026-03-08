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
    @PostMapping("add-order")
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
    @GetMapping("get-orders")
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
}