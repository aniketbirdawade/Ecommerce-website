package com.ecommerce.Entity;

import jakarta.persistence.*;

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
    private HsnData hsn;

    public Category() {
    }

    public Category(int id, String name, String description,
                    String img_url, HsnData hsn) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img_url = img_url;
        this.hsn = hsn;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public HsnData getHsn() {
        return hsn;
    }

    public void setHsn(HsnData hsn) {
        this.hsn = hsn;
    }
}