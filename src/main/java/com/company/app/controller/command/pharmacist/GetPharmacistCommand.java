package com.company.app.controller.command.pharmacist;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.PharmacistDto;
import com.company.app.service.ClientService;
import com.company.app.service.PharmacistService;
import jakarta.servlet.http.HttpServletRequest;

public class GetPharmacistCommand implements Command {
    private final PharmacistService pharmacistService;

    public GetPharmacistCommand(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        PharmacistDto pharmacist = pharmacistService.getById(id);
        req.setAttribute("pharmacist", pharmacist);
        return "jsp/pharmacist/pharmacist.jsp";
    }
}
