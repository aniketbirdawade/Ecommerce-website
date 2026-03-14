package com.ecommerce.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.Cart_item;
import com.ecommerce.Entity.Coupan;
import com.ecommerce.Entity.Delivary_Slot;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.Order_items;
import com.ecommerce.Entity.Product;
import com.ecommerce.Entity.User;
import com.ecommerce.Repository.AddressRepository;
import com.ecommerce.Repository.CartItemRepository;
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
    private final CartItemRepository cir;

    public OrderService(OrderRepository or,
                        UserRepository ur,
                        AddressRepository ar,
                        CoupanRepository cr,
                        DeliverySlotRepository dsr,
                        OrderItemRepository oir,
                        CartItemRepository cir) {

        this.or = or;
        this.ur = ur;
        this.ar = ar;
        this.cr = cr;
        this.dsr = dsr;
        this.oir = oir;
        this.cir = cir;
    }

    // ===============================
    // PLACE ORDER FROM CART
    // ===============================
    @Transactional
    public Order placeOrder(Integer userId) {

        User user = ur.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(ar.findById(1).orElseThrow());
        order.setCoupan(cr.findById(1).orElseThrow());
        order.setDelivary_slot(dsr.findById(1).orElseThrow());
        order.setOrder_status("PLACED");
        order.setPayment_status("PENDING");
        order.setCreated_at(new Timestamp(System.currentTimeMillis()));

        Order savedOrder = or.save(order);

        List<Cart_item> cartItems = cir.findByCartUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalGST = BigDecimal.ZERO;

        for (Cart_item cartItem : cartItems) {

            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            BigDecimal price = product.getPrice();

            // price × qty
            BigDecimal itemSubTotal = price.multiply(BigDecimal.valueOf(quantity));

            BigDecimal gstRate = BigDecimal.ZERO;

            if (product.getHsn() != null) {
                gstRate = BigDecimal.valueOf(product.getHsn().getGst_rate());
            }

            // GST calculation
            BigDecimal gstAmount = itemSubTotal
                    .multiply(gstRate)
                    .divide(BigDecimal.valueOf(100));

            // final price for item
            BigDecimal totalPrice = itemSubTotal.add(gstAmount);

            Order_items orderItem = new Order_items();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setGst_rate(gstRate);
            orderItem.setGst_amount(gstAmount);
            orderItem.setTotal_price(totalPrice);

            oir.save(orderItem);

            subTotal = subTotal.add(itemSubTotal);
            totalGST = totalGST.add(gstAmount);
        }

        // subtotal + GST
        BigDecimal orderTotal = subTotal.add(totalGST);

        BigDecimal discount = BigDecimal.ZERO;

        if (savedOrder.getCoupan() != null) {
            discount = savedOrder.getCoupan().getDiscount_value();
        }

        BigDecimal finalAmount = orderTotal.subtract(discount);

        savedOrder.setTotal_amt(orderTotal);
        savedOrder.setDiscount_amt(discount);
        savedOrder.setFinal_amt(finalAmount);

        // clear cart
        cir.deleteAll(cartItems);

        return or.save(savedOrder);
    }

    // ===============================
    // GET ALL ORDERS
    // ===============================
    public List<Order> getAllOrders() {
        return or.findAll();
    }

    // ===============================
    // GET ORDER BY ID
    // ===============================
    public Order getOrder(Integer id) {
        return or.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // ===============================
    // GET ORDERS BY USER
    // ===============================
    public List<Order> getOrdersByUser(Integer userId) {
        return or.findByUserId(userId);
    }

    // ===============================
    // GET ORDER ITEMS
    // ===============================
    public List<Order_items> getOrderItemsByOrderId(Integer orderId) {
        return oir.findByOrderId(orderId);
    }

    // ===============================
    // UPDATE ORDER STATUS
    // ===============================
    public Order updateStatus(int id, String status) {

        Order order = or.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrder_status(status);

        return or.save(order);
    }

    // ===============================
    // TRACK ORDER
    // ===============================
    public String trackOrder(int orderId) {

        Order order = or.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return order.getOrder_status();
    }

    // ===============================
    // CREATE ORDER MANUALLY
    // ===============================
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

    // ===============================
    // UPDATE ORDER
    // ===============================
    public Order updateOrder(Integer id, Order order) {

        Order existingOrder = or.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setOrder_status(order.getOrder_status());
        existingOrder.setPayment_status(order.getPayment_status());
        existingOrder.setFinal_amt(order.getFinal_amt());
        existingOrder.setDiscount_amt(order.getDiscount_amt());
        existingOrder.setTotal_amt(order.getTotal_amt());

        return or.save(existingOrder);
    }

    // ===============================
    // DELETE ORDER
    // ===============================
    public void deleteOrder(Integer id) {

        if (!or.existsById(id)) {
            throw new RuntimeException("Order not found");
        }

        or.deleteById(id);
    }
}