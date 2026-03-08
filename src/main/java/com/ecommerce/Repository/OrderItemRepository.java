package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Order_items;

public interface OrderItemRepository extends JpaRepository<Order_items, Integer> {
}