package com.ecommerce.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.Coupan;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.AddressRepository;
import com.ecommerce.Repository.CoupanRepository;
import com.ecommerce.Repository.DeliverySlotRepository;
import com.ecommerce.Repository.OrderItemRepository;
import com.ecommerce.Repository.OrderRepository;
import com.ecommerce.Repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository or;
    private final UserRepository ur;
    private final AddressRepository ar;
    private final CoupanRepository cr;
    private final DeliverySlotRepository dsr;
    private final OrderItemRepository oir;

    public OrderService(OrderRepository or,
                        UserRepository ur,
                        AddressRepository ar,
                        CoupanRepository cr,
                        DeliverySlotRepository dsr,
                        OrderItemRepository oir) {

        this.or = or;
        this.ur = ur;
        this.ar = ar;
        this.cr = cr;
        this.dsr = dsr;
        this.oir = oir;
    }

    // Create Order
    public Order createOrder(Order order) {

        order.setCreated_at(new Timestamp(System.currentTimeMillis()));

        User user = ur.findById(order.getUser().getId()).orElseThrow();
        Address address = ar.findById(order.getAddress().getId()).orElseThrow();
        Coupan coupan = cr.findById(order.getCoupan().getId()).orElseThrow();
        Delivary_Slot slot = dsr.findById(order.getDelivary_slot().getId()).orElseThrow();

        order.setUser(user);
        order.setAddress(address);
        order.setCoupan(coupan);
        order.setDelivary_slot(slot);

        return or.save(order);
    }

    // Get Order by ID
    public Order getOrder(Integer id) {
        return or.findById(id).orElse(null);
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return or.findAll();
    }

    // Get Orders by User
    public List<Order> getOrdersByUser(Integer userId) {
        return or.findByUserId(userId);
    }

    // Update Order
    public Order updateOrder(Integer id, Order order) {

        Order existing = or.findById(id).orElseThrow();

        existing.setOrder_status(order.getOrder_status());
        existing.setPayment_status(order.getPayment_status());
        existing.setFinal_amt(order.getFinal_amt());

        return or.save(existing);
    }

    // Delete Order
    public void deleteOrder(Integer id) {
        or.deleteById(id);
    }

    // Place Order
    public Order placeOrder(Integer userId) {

        User user = ur.findById(userId).orElseThrow();

        Order order = new Order();
        order.setUser(user);

        Address address = ar.findById(1).orElseThrow();
        Coupan coupan = cr.findById(1).orElseThrow();
        Delivary_Slot slot = dsr.findById(1).orElseThrow();

        order.setAddress(address);
        order.setCoupan(coupan);
        order.setDelivary_slot(slot);

        order.setOrder_status("PLACED");
        order.setPayment_status("PENDING");

        order.setTotal_amt(java.math.BigDecimal.ZERO);
        order.setDiscount_amt(java.math.BigDecimal.ZERO);
        order.setFinal_amt(java.math.BigDecimal.ZERO);

        order.setCreated_at(new Timestamp(System.currentTimeMillis()));

        return or.save(order);
    }

    // Get Order Items
    public List<Order_items> getOrderItemsByOrderId(Integer orderId) {
        return oir.findByOrderId(orderId);
    }

	public Order updateStatus(int id, String status) {
		Optional<Order> optionalOrder = or.findById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrder_status(status);
            return or.save(order);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
	}
	
	public String trackOrder(int orderId) {

        Order order = or.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        return order.getOrder_status();
    }
}