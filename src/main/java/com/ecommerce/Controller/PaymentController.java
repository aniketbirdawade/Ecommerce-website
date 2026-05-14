package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Payment;
import com.ecommerce.Repository.PaymentRepository;
 
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 
    private final PaymentRepository paymentRepository;
 
    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
 
    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        Payment saved = paymentRepository.save(payment);
        return paymentRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return paymentRepository.findById(id).orElse(null);
    }
 
    // GET payments by order
    @GetMapping("/order/{orderId}")
    public List<Payment> getPaymentsByOrder(@PathVariable int orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
 
    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable int id, @RequestBody Payment updated) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) return null;
        payment.setPayment_status(updated.getPayment_status());
        payment.setTransaction_id(updated.getTransaction_id());
        return paymentRepository.save(payment);
    }
 
    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable int id) {
        paymentRepository.deleteById(id);
        return "Payment deleted successfully";
    }
}