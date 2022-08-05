package com.company.app.controller.command.drug;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AllDrugsCommand implements Command {
    private final DrugService drugService;

    public AllDrugsCommand(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<DrugDto> drugs = drugService.getAll();
        req.setAttribute("drugs", drugs);
        return "jsp/drugs.jsp";
    }
}
