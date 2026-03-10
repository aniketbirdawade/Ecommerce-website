package com.ecommerce.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.Service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderTrackingController {

    private final OrderService os;

    public OrderTrackingController(OrderService os) {
        this.os = os;
    }

    @GetMapping("/{id}/track")
    public String trackOrder(@PathVariable int id) {
        return os.trackOrder(id);
    }
}
