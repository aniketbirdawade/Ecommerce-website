package com.ecommerce.Entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivary_slot")
public class Delivary_Slot 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;
	private Time start_time;
	private Time end_time;
	private int max_order;
	private int avaliable_order;
	
	public Delivary_Slot() {
		super();
	}

	public Delivary_Slot(int id, Date date, Time start_time, Time end_time, int max_order, int avaliable_order) {
		super();
		this.id = id;
		this.date = date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.max_order = max_order;
		this.avaliable_order = avaliable_order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public int getMax_order() {
		return max_order;
	}

	public void setMax_order(int max_order) {
		this.max_order = max_order;
	}

	public int getAvaliable_order() {
		return avaliable_order;
	}

	public void setAvaliable_order(int avaliable_order) {
		this.avaliable_order = avaliable_order;
	}

	@Override
	public String toString() {
		return "Delivary_Slot [id=" + id + ", date=" + date + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", max_order=" + max_order + ", avaliable_order=" + avaliable_order + "]";
	}
	
	
}
