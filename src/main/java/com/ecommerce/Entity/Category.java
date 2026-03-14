package com.ecommerce.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    private HsnMaster hsn;

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
    
    public HsnMaster getHsn() {
        return hsn;
    }

    public void setHsn(HsnMaster hsn) {
        this.hsn = hsn;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imgUrl=" + img_url + "]";
    }
}