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
@Table(name = "product")
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal discount_price;
	private int stock_quantity;
	private String brand;
	private String weight;
	private String img_url;
	private boolean is_avaliable;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product() {
		super();
	}

	public Product(int id, String name, String description, BigDecimal price, BigDecimal discount_price,
			int stock_quantity, String brand, String weight, String img_url, boolean is_avaliable, Timestamp created_at,
			Timestamp updated_at, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount_price = discount_price;
		this.stock_quantity = stock_quantity;
		this.brand = brand;
		this.weight = weight;
		this.img_url = img_url;
		this.is_avaliable = is_avaliable;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(BigDecimal discount_price) {
		this.discount_price = discount_price;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public boolean isIs_avaliable() {
		return is_avaliable;
	}

	public void setIs_avaliable(boolean is_avaliable) {
		this.is_avaliable = is_avaliable;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", discount_price=" + discount_price + ", stock_quantity=" + stock_quantity + ", brand=" + brand
				+ ", weight=" + weight + ", img_url=" + img_url + ", is_avaliable=" + is_avaliable + ", created_at="
				+ created_at + ", updated_at=" + updated_at + ", category=" + category + "]";
	}
	
	
}
