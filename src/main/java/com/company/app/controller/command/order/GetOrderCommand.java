package com.company.app.controller.command.order;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.model.entity.Order;
import com.company.app.service.DrugService;
import com.company.app.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;

public class GetOrderCommand implements Command {
    private final OrderService orderService;

    public GetOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }
    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        OrderDto order = orderService.getById(id);
        req.setAttribute("order", order);
        return "jsp/order/order.jsp";
    }
}
