package com.ecommerce.Controller;
 
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Order;
import com.ecommerce.Repository.OrderRepository;
 
@RestController
@RequestMapping("/api/orders")
public class OrderController {
 
    private final OrderRepository orderRepository;
 
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
 
    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        Order saved = orderRepository.save(order);
        return orderRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return orderRepository.findById(id).orElse(null);
    }
 
    // GET orders by user
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable int userId) {
        return orderRepository.findByUserId(userId);
    }
 
    // UPDATE order status
    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return null;
        order.setOrder_status(status);
        return orderRepository.save(order);
    }
 
    // UPDATE payment status
    @PatchMapping("/{id}/payment-status")
    public Order updatePaymentStatus(@PathVariable int id, @RequestParam String status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return null;
        order.setPayment_status(status);
        return orderRepository.save(order);
    }
 
    // UPDATE discount — admin can set manually
    @PatchMapping("/{id}/discount")
    public Order updateDiscount(@PathVariable int id, @RequestParam BigDecimal amount) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return null;
        order.setDiscount_amt(amount);
        BigDecimal total = order.getTotal_amt() != null ? order.getTotal_amt() : BigDecimal.ZERO;
        order.setFinal_amt(total.subtract(amount));
        return orderRepository.save(order);
    }
 
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderRepository.deleteById(id);
        return "Order deleted successfully";
    }
}