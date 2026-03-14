package com.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entity.Cart_item;

public interface CartItemRepository extends JpaRepository<Cart_item, Integer>{

	List<Cart_item> findByCart_Id(int cartId);

	List<Cart_item> findByCartUserId(Integer userId);
}
