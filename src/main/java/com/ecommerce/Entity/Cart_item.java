package com.ecommerce.Entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_item")
public class Cart_item 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int quantity;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal final_price;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Cart_item() {
		super();
	}

	public Cart_item(int id, int quantity, BigDecimal final_price, Cart cart, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.final_price = final_price;
		this.cart = cart;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getFinal_price() {
		return final_price;
	}

	public void setFinal_price(BigDecimal final_price) {
		this.final_price = final_price;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cart_item [id=" + id + ", quantity=" + quantity + ", final_price=" + final_price + ", cart=" + cart
				+ ", product=" + product + "]";
	}

	
}
