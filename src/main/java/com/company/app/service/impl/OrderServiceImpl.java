package com.company.app.service.impl;

import com.company.app.dao.DrugDao;
import com.company.app.dao.OrderDao;
import com.company.app.model.converter.DrugConverter;
import com.company.app.model.converter.OrderConverter;
import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.entity.Order;
import com.company.app.service.OrderService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final DrugDao drugDao;
    private final OrderConverter orderConverter;
    private final DrugConverter drugConverter;

    public OrderServiceImpl(OrderDao orderDao, DrugDao drugDao, OrderConverter orderConverter, DrugConverter drugConverter) {
        this.orderDao = orderDao;
        this.drugDao = drugDao;
        this.orderConverter = orderConverter;
        this.drugConverter = drugConverter;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderConverter.convertEntityToDto(orderDao.getById(id));
    }

    @Override
    public OrderDto saveOrUpdate(OrderDto orderDto) {
        Order order = orderDao.saveOrUpdate(orderConverter.convertDtoToEntity(orderDto));
        return orderConverter.convertEntityToDto(order);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderConverter.convertEntitiesToDtos(orderDao.getAll());
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

//    @Override
//    public OrderDto created(Map<Long, Integer> cart, ClientDto clientDto) {
//        OrderDto order = new OrderDto();
//        Map<DrugDto, Integer> drugs = new HashMap<>();
//        BigDecimal totalCoast = BigDecimal.ZERO;
//        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
//            DrugDto drug = drugConverter.convertEntityToDto(drugDao.getById(entry.getKey()));
//            Integer drugQuantity = entry.getValue();
//            totalCoast = totalCoast.add(drug.getPrice().multiply(BigDecimal.valueOf(drugQuantity)));
//            drugs.put(drug, drugQuantity);
//        }
//        order.setClient(clientDto);
//        order.setDrugs(drugs);
//        order.setStatus(OrderDto.OrderStatus.PROCESSING);
//        order.setTotalCoast(totalCoast);
//        return orderConverter.convertEntityToDto(orderDao.saveOrUpdate(orderConverter.convertDtoToEntity(order)));
//    }

    @Override
    public OrderDto processCart(Map<Long, Integer> cart, ClientDto clientDto) {
        OrderDto order = new OrderDto();
        Map<DrugDto, Integer> drugs = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            DrugDto drug = drugConverter.convertEntityToDto(drugDao.getById(entry.getKey()));
            Integer drugQuantity = entry.getValue();
            drugs.put(drug, drugQuantity);
        }
        order.setClient(clientDto);
        order.setDrugs(drugs);
        order.setStatus(OrderDto.OrderStatus.PROCESSING);
        BigDecimal totalCoast = calculatePrice(drugs);
        order.setTotalCoast(totalCoast);
        return order;
    }

    private BigDecimal calculatePrice(Map<DrugDto, Integer> drugs){
        BigDecimal totalCoast = BigDecimal.ZERO;
        for (Map.Entry<DrugDto, Integer> entry : drugs.entrySet()) {
            DrugDto drug = entry.getKey();
            Integer drugQuantity = entry.getValue();
            totalCoast = totalCoast.add(drug.getPrice().multiply(BigDecimal.valueOf(drugQuantity)));
        }
        return totalCoast;
    }
}
