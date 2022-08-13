package com.company.app.controller.command;

import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddToCartCommand implements Command {

    private final DrugService drugService;

    public AddToCartCommand(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long drugId = Long.parseLong(req.getParameter("drugId"));
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        Integer quantity = cart.get(drugId);
        if (quantity == null) {
            cart.put(drugId, 1);
        } else {
            cart.put(drugId, quantity+1);
        }
        session.setAttribute("cart", cart);
//       Тут ХЗ как на drugs правильно перейти???
        /*
        Добавление в карзину происходит из разных мест:
        1)jsp/drug/drugs.jsp;
        2)jsp/showCart.jsp;
        Нужно возвращаться на ту форму откуда происходит добавление в корзину
         */
        List<DrugDto> drugs = drugService.getAll();
        req.setAttribute("drugs", drugs);
        return "jsp/drug/drugs.jsp";
    }
}
