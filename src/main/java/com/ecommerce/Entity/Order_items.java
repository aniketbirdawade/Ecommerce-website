package com.ecommerce.Entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

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
    private BigDecimal discount_amt = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private DiscountType discount_type;

    @Column(precision = 10, scale = 2)
    private BigDecimal final_price = BigDecimal.ZERO;

    public Order_items() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount_amt() {
        return discount_amt;
    }

    public void setDiscount_amt(BigDecimal discount_amt) {
        this.discount_amt = discount_amt;
    }

    public DiscountType getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(DiscountType discount_type) {
        this.discount_type = discount_type;
    }

    public BigDecimal getFinal_price() {
        return final_price;
    }

    public void setFinal_price(BigDecimal final_price) {
        this.final_price = final_price;
    }
}