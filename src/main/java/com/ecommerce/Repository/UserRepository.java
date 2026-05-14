package com.ecommerce.Repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.User;
 
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByNameContainingIgnoreCase(String name);
}