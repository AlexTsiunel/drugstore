package com.company.app.model.converter;

import com.company.app.model.api.Convert;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.entity.Drug;
import com.company.app.model.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderConverter implements Convert<OrderDto, Order> {
    private ClientConverter clientConverter;
    private DrugConverter drugConverter;
    private PharmacistConverter pharmacistConverter;

    public OrderConverter(ClientConverter clientConverter, DrugConverter drugConverter, PharmacistConverter pharmacistConverter) {
        this.clientConverter = clientConverter;
        this.drugConverter = drugConverter;
        this.pharmacistConverter = pharmacistConverter;
    }

    @Override
    public OrderDto convertEntityToDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        if (entity != null) {
            orderDto.setId(entity.getId());
            orderDto.setClient(clientConverter.convertEntityToDto(entity.getClient()));
            orderDto.setDrugs(toDtosMap(entity.getDrugs()));
            orderDto.setPharmacist(pharmacistConverter.convertEntityToDto(entity.getPharmacist()));
            orderDto.setStatus(toOrderStatusDto(entity.getStatus()));
            orderDto.setDeleted(entity.isDeleted());
        }
        return orderDto;
    }

    @Override
    public Order convertDtoToEntity(OrderDto dto) {
        Order order = new Order();
        if (dto != null) {
            order.setId(dto.getId());
            order.setClient(clientConverter.convertDtoToEntity(dto.getClient()));
            order.setDrugs(toEntitiesMap(dto.getDrugs()));
            order.setPharmacist(pharmacistConverter.convertDtoToEntity(dto.getPharmacist()));
            order.setStatus(toOrderStatusEntity(dto.getStatus()));
            order.setDeleted(dto.isDeleted());
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
