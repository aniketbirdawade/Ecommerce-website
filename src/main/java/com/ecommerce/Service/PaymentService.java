package com.ecommerce.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Payment;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository pr;
    private final OrderRepository or;

    public PaymentService(PaymentRepository pr, OrderRepository or) {
        this.pr = pr;
        this.or = or;
    }

    public Payment createPayment(Payment payment) {
        payment.setPayment_date(new Timestamp(System.currentTimeMillis()));
        Order order = or.findById(payment.getOrder().getId()).orElseThrow();
        
        payment.setOrder(order);
        
        return pr.save(payment);
    }

    public List<Payment> getAllPayments() {
        return pr.findAll();
    }

    public Payment getPayment(Integer id) {
        return pr.findById(id).orElse(null);
    }
}
