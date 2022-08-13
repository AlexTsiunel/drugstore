package com.company.app.controller.command;

import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.service.DrugService;
import com.company.app.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderCommand implements Command {
    private final OrderService orderService;

    public CreateOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        ClientDto client = (ClientDto) session.getAttribute("client");

        OrderDto created = orderService.created(cart, client);

        req.setAttribute("order", created);
        req.setAttribute("massage", "Order successfully created");
        return "jsp/order/order.jsp";
    }
}
