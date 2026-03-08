package com.ecommerce.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.Coupan;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.AddressRepository;
import com.ecommerce.Repository.CoupanRepository;
import com.ecommerce.Repository.DeliverySlotRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository or;
    private final UserRepository ur;
    private final AddressRepository ar;
    private final CoupanRepository cr;
    private final DeliverySlotRepository dsr;

    public OrderService(OrderRepository or, UserRepository ur, AddressRepository ar, CoupanRepository cr, DeliverySlotRepository dsr) {
        this.or = or;
        this.ur = ur;
        this.ar = ar;
        this.cr = cr;
        this.dsr = dsr;
        
    }

    public Order createOrder(Order order) {

        order.setCreated_at(new Timestamp(System.currentTimeMillis()));
        User user = ur.findById(order.getUser().getId()).orElseThrow();
        Address addr = ar.findById(order.getUser().getId()).orElseThrow();
        Coupan coupan = cr.findById(order.getUser().getId()).orElseThrow();
        Delivary_Slot slot = dsr.findById(order.getUser().getId()).orElseThrow();
        
        order.setUser(user);
        order.setAddress(addr);
        order.setCoupan(coupan);
        order.setDelivary_slot(slot);
        return or.save(order);
    }

    public Order getOrder(Integer id) {
        return or.findById(id).orElse(null);
    }

    public List<Order> getAllOrders() {
        return or.findAll();
    }

    public void deleteOrder(Integer id) {
        or.deleteById(id);
    }

    public Order updateOrder(Integer id, Order order) {

        Order existing = or.findById(id).orElseThrow();

        existing.setOrder_status(order.getOrder_status());
        existing.setPayment_status(order.getPayment_status());
        existing.setFinal_amt(order.getFinal_amt());

        return or.save(existing);
    }
}