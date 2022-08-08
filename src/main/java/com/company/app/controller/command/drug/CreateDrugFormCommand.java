package com.company.app.controller.command.drug;

import com.company.app.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class CreateDrugFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/drug/createDrugForm.jsp";
    }
}
