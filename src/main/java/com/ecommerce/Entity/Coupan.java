package com.ecommerce.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "coupan")
public class Coupan 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String discount_type;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal discount_value;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal min_order_amount;
	private Date expiry_date;
	private boolean is_active;
	
	public Coupan() {
		super();
	}

	public Coupan(int id, String code, String discount_type, BigDecimal discount_value, BigDecimal min_order_amount,
			Date expiry_date, boolean is_active) {
		super();
		this.id = id;
		this.code = code;
		this.discount_type = discount_type;
		this.discount_value = discount_value;
		this.min_order_amount = min_order_amount;
		this.expiry_date = expiry_date;
		this.is_active = is_active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

	public BigDecimal getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(BigDecimal discount_value) {
		this.discount_value = discount_value;
	}

	public BigDecimal getMin_order_amount() {
		return min_order_amount;
	}

	public void setMin_order_amount(BigDecimal min_order_amount) {
		this.min_order_amount = min_order_amount;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	@Override
	public String toString() {
		return "Coupan [id=" + id + ", code=" + code + ", discount_type=" + discount_type + ", discount_value="
				+ discount_value + ", min_order_amount=" + min_order_amount + ", expiry_date=" + expiry_date
				+ ", is_active=" + is_active + "]";
	}
	
	
	
}
