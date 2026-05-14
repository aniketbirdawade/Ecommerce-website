package com.ecommerce.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;
import com.ecommerce.Entity.DiscountType;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Repository.OrderItemRepository;
import com.ecommerce.Repository.OrderRepository;

@Service
public class DiscountService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public DiscountService(OrderItemRepository orderItemRepository,
                           OrderRepository orderRepository) {

        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }
}
    
 