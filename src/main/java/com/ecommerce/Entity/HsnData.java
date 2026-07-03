package com.ecommerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hsn_data")
public class HsnData {

    @Id
    @Column(name = "hsn_code", nullable = false, length = 20)
    private String hsn_code;

    private String description;

    @Column(name = "gst_rate")
    private double gst_rate;

    public HsnData() {
    }

    public HsnData(String hsn_code, String description, double gst_rate) {
        this.hsn_code = hsn_code;
        this.description = description;
        this.gst_rate = gst_rate;
    }

    public String getHsn_code() {
        return hsn_code;
    }

    public void setHsn_code(String hsn_code) {
        this.hsn_code = hsn_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGst_rate() {
        return gst_rate;
    }

    public void setGst_rate(double gst_rate) {
        this.gst_rate = gst_rate;
    }

    @Override
    public String toString() {
        return "HsnData [hsn_code=" + hsn_code + ", description=" + description + ", gst_rate=" + gst_rate + "]";
    }
}
