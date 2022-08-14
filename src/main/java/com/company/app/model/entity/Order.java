package com.company.app.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order extends PersistableEntityImpl {
    private Client client;
    private Pharmacist pharmacist;
    private BigDecimal totalCoast;
    private OrderStatus status;
    private List<OrderInfo> orderInfoList;
    private boolean deleted;

    public enum OrderStatus{
        PROCESSING,
        PENDING,
        PAID,
        CANCELED
    }
}
