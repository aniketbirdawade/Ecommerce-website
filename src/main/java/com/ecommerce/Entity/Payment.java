package com.ecommerce.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String payment_method;
    private String payment_status;
    private String transaction_id;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    private Timestamp payment_date;

	public Payment() {
		super();
	}

	public Payment(int id, Order order, String payment_method, String payment_status, String transaction_id,
			BigDecimal amount, Timestamp payment_date) {
		super();
		this.id = id;
		this.order = order;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.payment_date = payment_date;
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

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Timestamp getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Timestamp payment_date) {
		this.payment_date = payment_date;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", order=" + order + ", payment_method=" + payment_method + ", payment_status="
				+ payment_status + ", transaction_id=" + transaction_id + ", amount=" + amount + ", payment_date="
				+ payment_date + "]";
	}
    
    
}
