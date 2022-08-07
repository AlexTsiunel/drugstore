package com.company.app.controller.command.order;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.service.DrugService;
import com.company.app.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AllOrdersCommand implements Command {
    private final OrderService orderService;

    public AllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<OrderDto> orders = orderService.getAll();
        req.setAttribute("orders", orders);
        return "jsp/order/orders.jsp";
    }
}
