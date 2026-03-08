package com.ecommerce.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Entity.Product;
import com.ecommerce.Repository.OrderItemRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.ProductRepository;

@Service
public class OrderItemService {

    private final OrderItemRepository oir;
    private final OrderRepository or;
    private final ProductRepository pr;

    public OrderItemService(OrderItemRepository oir, OrderRepository or, ProductRepository pr) {
        this.oir = oir;
        this.or = or;
        this.pr = pr;
    }

    public Order_items addOrderItem(Order_items item) {
        Order order = or.findById(item.getOrder().getId()).orElseThrow();
        Product product = pr.findById(item.getProduct().getId()).orElseThrow();

        item.setOrder(order);
        item.setProduct(product);
        item.setPurchase_price(product.getPrice().multiply(new BigDecimal(item.getQuantity())));

        return oir.save(item);
    }

    public List<Order_items> getAllItems() {
        return oir.findAll();
    }

    public Order_items getItem(Integer id) {
        return oir.findById(id).orElse(null);
    }

    public void deleteItem(Integer id) {
        oir.deleteById(id);
    }
}