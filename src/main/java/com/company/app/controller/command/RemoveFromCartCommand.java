package com.company.app.controller.command;

import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;


public class RemoveFromCartCommand implements Command {
    private final DrugService drugService;

    public RemoveFromCartCommand(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long drugId = Long.parseLong(req.getParameter("drugId"));
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart != null && cart.containsKey(drugId)) {
            Integer quantity = cart.get(drugId);
            quantity--;
            cart.put(drugId,quantity);
            if (quantity == 0) {
                cart.remove(drugId);
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:controller" + req.getParameter("redirect");
    }
}
