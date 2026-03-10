package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Get all orders by user
    List<Order> findByUserId(Integer userId);

}