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
@Table(name = "orders")
public class Order 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;
	
	@ManyToOne
	@JoinColumn(name = "coupan_id", nullable = false)
	private Coupan coupan;
	
	@ManyToOne
	@JoinColumn(name = "delivary_slot_id", nullable = false)
	private Delivary_Slot delivary_slot;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal total_amt;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal discount_amt;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal final_amt;
	
	private String order_status;
	private String payment_status;
	private  Timestamp created_at;
	
	public Order() {
		super();
	}
	public Order(int id, User user, Address address, Coupan coupan, Delivary_Slot delivary_slot, BigDecimal total_amt,
			BigDecimal discount_amt, BigDecimal final_amt, String order_status, String payment_status,
			Timestamp created_at) {
		super();
		this.id = id;
		this.user = user;
		this.address = address;
		this.coupan = coupan;
		this.delivary_slot = delivary_slot;
		this.total_amt = total_amt;
		this.discount_amt = discount_amt;
		this.final_amt = final_amt;
		this.order_status = order_status;
		this.payment_status = payment_status;
		this.created_at = created_at;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Coupan getCoupan() {
		return coupan;
	}
	public void setCoupan(Coupan coupan) {
		this.coupan = coupan;
	}
	public Delivary_Slot getDelivary_slot() {
		return delivary_slot;
	}
	public void setDelivary_slot(Delivary_Slot delivary_slot) {
		this.delivary_slot = delivary_slot;
	}
	public BigDecimal getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(BigDecimal total_amt) {
		this.total_amt = total_amt;
	}
	public BigDecimal getDiscount_amt() {
		return discount_amt;
	}
	public void setDiscount_amt(BigDecimal discount_amt) {
		this.discount_amt = discount_amt;
	}
	public BigDecimal getFinal_amt() {
		return final_amt;
	}
	public void setFinal_amt(BigDecimal final_amt) {
		this.final_amt = final_amt;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", address=" + address + ", coupan=" + coupan + ", delivary_slot="
				+ delivary_slot + ", total_amt=" + total_amt + ", discount_amt=" + discount_amt + ", final_amt="
				+ final_amt + ", order_status=" + order_status + ", payment_status=" + payment_status + ", created_at="
				+ created_at + "]";
	}
	
}
