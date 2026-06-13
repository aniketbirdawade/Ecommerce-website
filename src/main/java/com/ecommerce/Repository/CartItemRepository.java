package com.ecommerce.Repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.CartItem;
 
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartId(int cartId);
}