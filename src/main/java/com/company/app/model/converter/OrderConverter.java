package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.entity.Drug;
import com.company.app.model.entity.Order;

import java.util.HashMap;
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
            orderDto.setDrugs(toDtosMap(entity.getDrugs()));
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
            order.setDrugs(toEntitiesMap(orderDto.getDrugs()));
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

    private Map<Drug, Integer> toEntitiesMap(Map<DrugDto, Integer> mapDto) {
        Map<Drug, Integer> mapEntity = new HashMap<>();
        if (mapDto != null) {
            for (Map.Entry<DrugDto, Integer> entry : mapDto.entrySet()) {
                mapEntity.put(drugConverter.convertDtoToEntity(entry.getKey()), entry.getValue());
            }
        }
        return mapEntity;
    }

    private Map<DrugDto, Integer> toDtosMap(Map<Drug, Integer> mapEntity) {
        Map<DrugDto, Integer> mapDto = new HashMap<>();
        if (mapEntity != null) {
            for (Map.Entry<Drug, Integer> entry : mapEntity.entrySet()) {
                mapDto.put(drugConverter.convertEntityToDto(entry.getKey()), entry.getValue());
            }
        }
        return mapDto;
    }
}
