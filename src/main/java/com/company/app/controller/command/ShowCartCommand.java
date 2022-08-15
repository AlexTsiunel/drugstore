package com.company.app.controller.command;

import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.DrugDto;
import com.company.app.model.dto.OrderDto;
import com.company.app.service.DrugService;
import com.company.app.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;

public class ShowCartCommand implements Command {
    public static final String PAGE = "jsp/showCart.jsp";
//    private final DrugService drugService;
    private final OrderService orderService;

//    public ShowCartCommand(DrugService drugService) {
//        this.drugService = drugService;
//    }


    public ShowCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        ClientDto client = (ClientDto) session.getAttribute("client");
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            req.setAttribute("message", "Cart is empty");
            return PAGE;
        }
        OrderDto processed = orderService.processCart(cart, client);
        req.setAttribute("client", client);
        req.setAttribute("cart", processed);
        return PAGE;
    }
}
