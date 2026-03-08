package com.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Entity.Payment;
import com.ecommerce.Service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService ps;

    public PaymentController(PaymentService ps) {
        this.ps = ps;
    }

    @PostMapping("add-payment")
    public Payment createPayment(@RequestBody Payment payment) {
        return ps.createPayment(payment);
    }

    @GetMapping("get-payment")
    public List<Payment> getAllPayments() {
        return ps.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Integer id) {
        return ps.getPayment(id);
    }
}
