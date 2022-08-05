package com.company.app.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Order extends PersistableEntityImpl {
    private Client client;
    private Pharmacist pharmacist;
    private Map<Drug, Integer> drugs;
    private BigDecimal totalCoast;
    private OrderStatus status;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        AWAITING_PAYMENT,
        PAID,
        CANCELED
    }
}
