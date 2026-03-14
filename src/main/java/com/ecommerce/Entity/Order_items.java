package com.ecommerce.Entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class Order_items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(precision = 10, scale = 2)
    private BigDecimal gst_rate;

    @Column(precision = 10, scale = 2)
    private BigDecimal gst_amount;

    @Column(precision = 10, scale = 2)
    private BigDecimal total_price;

    public Order_items() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Order getOrder() { return order; }

    public void setOrder(Order order) { this.order = order; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getGst_rate() { return gst_rate; }

    public void setGst_rate(BigDecimal gst_rate) { this.gst_rate = gst_rate; }

    public BigDecimal getGst_amount() { return gst_amount; }

    public void setGst_amount(BigDecimal gst_amount) { this.gst_amount = gst_amount; }

    public BigDecimal getTotal_price() { return total_price; }

    public void setTotal_price(BigDecimal total_price) { this.total_price = total_price; }
}