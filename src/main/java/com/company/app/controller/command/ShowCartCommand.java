package com.company.app.controller.command;

import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.util.Map;

public class ShowCartCommand implements Command {
    private final DrugService drugService;

    public ShowCartCommand(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        @SuppressWarnings("unchecked")
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");

        //Логику подсчета totalCoast вынести в сервис
        Map<DrugDto, Integer> drugs = drugService.convertCartToDrugsMap(cart);
        BigDecimal totalCoast = BigDecimal.ZERO;
        for (Map.Entry<DrugDto, Integer> entry : drugs.entrySet()) {
            BigDecimal entryCoast = entry.getKey().getPrice();
            BigDecimal totalEntryCoast = entryCoast.multiply(BigDecimal.valueOf(entry.getValue()));
            totalCoast = totalCoast.add(totalEntryCoast);
        }
        req.setAttribute("drugs", drugs);
        req.setAttribute("totalCoast", totalCoast);
        return "jsp/showCart.jsp";
    }
}
