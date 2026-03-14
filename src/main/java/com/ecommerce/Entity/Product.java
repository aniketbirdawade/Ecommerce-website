package com.ecommerce.Entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

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

    // ⭐ NEW (HSN mapping)
    @ManyToOne
    @JoinColumn(name = "hsn_code")
    private HsnMaster hsn;

    public Product() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public HsnMaster getHsn() { return hsn; }

    public void setHsn(HsnMaster hsn) { this.hsn = hsn; }
}