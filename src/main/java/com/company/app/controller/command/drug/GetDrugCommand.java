package com.company.app.controller.command.drug;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;

public class GetDrugCommand implements Command {
    private final DrugService drugService;

    public GetDrugCommand(DrugService drugService) {
        this.drugService = drugService;
    }
    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        DrugDto drug = drugService.getById(id);
        req.setAttribute("drug", drug);
        return "jsp/drug/drug.jsp";
    }
}
