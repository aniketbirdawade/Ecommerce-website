package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
