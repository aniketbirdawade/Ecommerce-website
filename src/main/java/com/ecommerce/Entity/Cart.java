package com.ecommerce.Entity;

import java.sql.Timestamp;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Timestamp created_at;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Cart() {
		super();
	}

	public Cart(int id, Timestamp created_at, User user) {
		super();
		this.id = id;
		this.created_at = created_at;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", created_at=" + created_at + ", user=" + user + "]";
	}
	
	
}
