package com.ecommerce.Repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.Entity.Coupon;
 
public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}