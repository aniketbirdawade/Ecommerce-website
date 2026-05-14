package com.ecommerce.dto;

import java.math.BigDecimal;

import com.ecommerce.Entity.DiscountType;

public class DiscountRequest {

    private BigDecimal value;
    private DiscountType type;

    public DiscountRequest() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }
}
