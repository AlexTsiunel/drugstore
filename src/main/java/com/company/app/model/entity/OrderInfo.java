package com.company.app.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderInfo extends PersistableEntityImpl {
    private Drug drug;
    private Long orderId;
    private Integer drugQuantity;
    private BigDecimal drugPrice;
    private boolean deleted;
}
