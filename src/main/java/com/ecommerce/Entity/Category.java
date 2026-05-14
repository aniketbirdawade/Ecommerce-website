package com.ecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
  
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String img_url;

    @ManyToOne
    @JoinColumn(name = "hsn_code")
    private hsndata hsn;

    public Category() {}

    public Category(int id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
    }

    // Getters and Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImgUrl() { 
    	return img_url; 
    	}
    public void setImgUrl(String imgUrl) { this.img_url = imgUrl; }
    
    public hsndata getHsn() {
        return hsn;
    }

    public void setHsn(hsndata hsn) {
        this.hsn = hsn;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imgUrl=" + img_url + "]";
    }
}