package com.ecommerce.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hsndata")
public class hsndata 
{

    @Id
    private String hsn_code;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double gst_rate;

    public hsndata() {
    }

    public hsndata(String hsn_code, String description, Double gst_rate) {
		super();
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

    public Double getGst_rate() {
        return gst_rate;
    }

    public void setGst_rate(Double gst_rate) {
        this.gst_rate = gst_rate;
    }

	@Override
	public String toString() {
		return "HsnMaster [hsn_code=" + hsn_code + ", description=" + description + ", gst_rate=" + gst_rate + "]";
	}
   
    
}