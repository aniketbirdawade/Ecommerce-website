package com.ecommerce.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class Payments 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	private String payment_method;
	private String transaction_id;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal amount;
	
	private String payment_status;
	private Timestamp paid_at;
	
	public Payments() {
		super();
	}

	public Payments(int id, Order order, String payment_method, String transaction_id, BigDecimal amount,
			String payment_status, Timestamp paid_at) {
		super();
		this.id = id;
		this.order = order;
		this.payment_method = payment_method;
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.payment_status = payment_status;
		this.paid_at = paid_at;
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

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Timestamp getPaid_at() {
		return paid_at;
	}

	public void setPaid_at(Timestamp paid_at) {
		this.paid_at = paid_at;
	}

	@Override
	public String toString() {
		return "Payments [id=" + id + ", order=" + order + ", payment_method=" + payment_method + ", transaction_id="
				+ transaction_id + ", amount=" + amount + ", payment_status=" + payment_status + ", paid_at=" + paid_at
				+ "]";
	}
	
	
}
