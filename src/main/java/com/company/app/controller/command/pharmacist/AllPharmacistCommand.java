package com.company.app.controller.command.pharmacist;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.ClientDto;
import com.company.app.model.dto.PharmacistDto;
import com.company.app.service.ClientService;
import com.company.app.service.PharmacistService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AllPharmacistCommand implements Command {
    private final PharmacistService pharmacistService;

    public AllPharmacistCommand(PharmacistService pharmacistService) {
        this.pharmacistService = pharmacistService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<PharmacistDto> pharmacists = pharmacistService.getAll();
        req.setAttribute("pharmacists", pharmacists);
        return "jsp/pharmacist/pharmacists.jsp";
    }
}
