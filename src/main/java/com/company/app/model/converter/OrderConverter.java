package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.dto.PharmacistDto;
import com.company.app.model.entity.Drug;
import com.company.app.model.entity.Order;
import com.company.app.model.entity.OrderInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderConverter extends Convert<OrderDto, Order> {
    private ClientConverter clientConverter;
    private PharmacistConverter pharmacistConverter;
    private DrugConverter drugConverter;

    public OrderConverter(ClientConverter clientConverter, PharmacistConverter pharmacistConverter, DrugConverter drugConverter) {
        this.clientConverter = clientConverter;
        this.pharmacistConverter = pharmacistConverter;
        this.drugConverter = drugConverter;
    }

    @Override
    public OrderDto convertEntityToDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        if (entity != null) {
            orderDto.setId(entity.getId());
            orderDto.setClient(clientConverter.convertEntityToDto(entity.getClient()));
            orderDto.setPharmacist(pharmacistConverter.convertEntityToDto(entity.getPharmacist()));
            orderDto.setDrugs(getMapDrugs(entity));
            orderDto.setTotalCoast(entity.getTotalCoast());
            orderDto.setStatus(toOrderStatusDto(entity.getStatus()));
            orderDto.setDeleted(entity.isDeleted());
        }
        return orderDto;
    }

    @Override
    public Order convertDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        if (orderDto != null) {
            order.setId(orderDto.getId());
            order.setClient(clientConverter.convertDtoToEntity(orderDto.getClient()));
            PharmacistDto pharmacistDto = orderDto.getPharmacist();
            if (pharmacistDto != null) {
                order.setPharmacist(pharmacistConverter.convertDtoToEntity(orderDto.getPharmacist()));
            }
            order.setOrderInfoList(getOrderInfoList(orderDto));
            order.setTotalCoast(orderDto.getTotalCoast());
            order.setStatus(toOrderStatusEntity(orderDto.getStatus()));
            order.setDeleted(orderDto.isDeleted());
        }
        return order;
    }

    private Order.OrderStatus toOrderStatusEntity(OrderDto.OrderStatus dtoOrderStatus) {
        int ordinal = dtoOrderStatus.ordinal();
        Order.OrderStatus[] entityOrderStatuses = Order.OrderStatus.values();
        return entityOrderStatuses[ordinal];
    }

    private OrderDto.OrderStatus toOrderStatusDto(Order.OrderStatus orderStatus) {
        int ordinal = orderStatus.ordinal();
        OrderDto.OrderStatus[] dtoOrderStatuses = OrderDto.OrderStatus.values();
        return dtoOrderStatuses[ordinal];
    }

    private Map<DrugDto, Integer> getMapDrugs(Order entity) {
        Map<DrugDto, Integer> drugs = new HashMap<>();
        List<OrderInfo> orderInfoList = entity.getOrderInfoList();
        for (OrderInfo orderInfo : orderInfoList) {
            Drug drug = orderInfo.getDrug();
            drug.setPrice(orderInfo.getDrugPrice());
            drugs.put(drugConverter.convertEntityToDto(drug), orderInfo.getDrugQuantity());
        }
        return drugs;
    }

    private List<OrderInfo> getOrderInfoList(OrderDto orderDto) {
        List<OrderInfo> list = new ArrayList<>();
        Map<DrugDto, Integer> drugs = orderDto.getDrugs();
        for (Map.Entry<DrugDto, Integer> entry : drugs.entrySet()) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setDrug(drugConverter.convertDtoToEntity(entry.getKey()));
            orderInfo.setOrderId(orderDto.getId());
            orderInfo.setDrugQuantity(entry.getValue());
            orderInfo.setDrugPrice(entry.getKey().getPrice());
            list.add(orderInfo);
        }
        return list;
    }
}
