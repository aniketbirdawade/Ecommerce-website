package com.ecommerce.Controller;
 
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Repository.OrderItemRepository;
 
@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
 
    private final OrderItemRepository orderItemRepository;
 
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
 
    @PostMapping
    public Order_items addOrderItem(@RequestBody Order_items orderItem) {
        Order_items saved = orderItemRepository.save(orderItem);
        return orderItemRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Order_items> getAllOrderItems() {
        return orderItemRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Order_items getOrderItemById(@PathVariable int id) {
        return orderItemRepository.findById(id).orElse(null);
    }
 
    // GET items by order
    @GetMapping("/order/{orderId}")
    public List<Order_items> getItemsByOrder(@PathVariable int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
 
    @PutMapping("/{id}")
    public Order_items updateOrderItem(@PathVariable int id, @RequestBody Order_items updated) {
        Order_items item = orderItemRepository.findById(id).orElse(null);
        if (item == null) return null;
        item.setQuantity(updated.getQuantity());
        item.setPrice(updated.getPrice());
        return orderItemRepository.save(item);
    }
 
    @DeleteMapping("/{id}")
    public String deleteOrderItem(@PathVariable int id) {
        orderItemRepository.deleteById(id);
        return "Order item deleted successfully";
    }
    
    @PatchMapping("/{id}/discount")
    public Order_items updateItemDiscount(@PathVariable int id,
                                          @RequestParam BigDecimal amount) {

        Order_items item = orderItemRepository.findById(id).orElse(null);
        if (item == null) return null;

        // set discount
        item.setDiscount_amt(amount);

        // recalculate final price for this item
        BigDecimal basePrice = item.getPrice() != null ? item.getPrice() : BigDecimal.ZERO;
        int qty = item.getQuantity();
        BigDecimal total = basePrice.multiply(BigDecimal.valueOf(qty));
        item.setFinal_price(total.subtract(amount));

        return orderItemRepository.save(item);
    }
}