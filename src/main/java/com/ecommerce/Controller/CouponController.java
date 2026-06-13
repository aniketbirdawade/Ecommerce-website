package com.ecommerce.Controller;
 
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.Entity.Coupon;
import com.ecommerce.Repository.CouponRepository;
 
@RestController
@RequestMapping("/api/coupons")
public class CouponController {
 
    private final CouponRepository coupanRepository;
 
    public CouponController(CouponRepository coupanRepository) {
        this.coupanRepository = coupanRepository;
    }
 
    @PostMapping
    public Coupon addCoupon(@RequestBody Coupon coupan) {
        Coupon saved = coupanRepository.save(coupan);
        return coupanRepository.findById(saved.getId()).orElse(saved);
    }
 
    @GetMapping
    public List<Coupon> getAllCoupons() {
        return coupanRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable int id) {
        return coupanRepository.findById(id).orElse(null);
    }
 
    @PutMapping("/{id}")
    public Coupon updateCoupon(@PathVariable int id, @RequestBody Coupon updated) {
        Coupon coupan = coupanRepository.findById(id).orElse(null);
        if (coupan == null) return null;
        coupan.setCode(updated.getCode());
        coupan.setDiscount_type(updated.getDiscount_type());
        coupan.setDiscount_value(updated.getDiscount_value());
        coupan.setMin_order_amount(updated.getMin_order_amount());
        coupan.setExpiry_date(updated.getExpiry_date());
        coupan.setIs_active(updated.isIs_active());
        return coupanRepository.save(coupan);
    }
 
    @DeleteMapping("/{id}")
    public String deleteCoupon(@PathVariable int id) {
        coupanRepository.deleteById(id);
        return "Coupon deleted successfully";
    }
}