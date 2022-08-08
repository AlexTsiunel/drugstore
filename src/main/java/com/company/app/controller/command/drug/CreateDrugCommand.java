package com.company.app.controller.command.drug;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.DrugDto;
import com.company.app.service.DrugService;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.Objects;

public class CreateDrugCommand implements Command {
    private final DrugService drugService;

    public CreateDrugCommand(DrugService drugService) {
        this.drugService = drugService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String name = req.getParameter("name");
        String releaseForm = req.getParameter("releaseForm");
        DrugDto.DosageForm dosageForm = DrugDto.DosageForm.valueOf(req.getParameter("dosageForm"));
        DrugDto.RouteAdministration routeAdministration = DrugDto.RouteAdministration.valueOf(req.getParameter("routeAdministration"));
        Boolean isRecipe = (Objects.equals(req.getParameter("recipe"), "true"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        Integer quantityInStock = Integer.parseInt(req.getParameter("quantityInStock"));
        DrugDto drugDto = new DrugDto();
        drugDto.setName(name);
        drugDto.setReleaseForm(releaseForm);
        drugDto.setDosageForm(dosageForm);
        drugDto.setRouteAdministration(routeAdministration);
        drugDto.setIsRecipe(isRecipe);
        drugDto.setPrice(price);
        drugDto.setQuantityInStock(quantityInStock);
        DrugDto newDrug = drugService.saveOrUpdate(drugDto);
        req.setAttribute("drug", newDrug);
        req.setAttribute("message", "New client was created");
        return "jsp/drug/drug.jsp";
    }
}
