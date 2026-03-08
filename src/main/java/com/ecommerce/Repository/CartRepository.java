package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
