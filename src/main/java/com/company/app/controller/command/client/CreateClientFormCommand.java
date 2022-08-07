package com.company.app.controller.command.client;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.ClientDto;
import com.company.app.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CreateClientFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/client/createClientForm.jsp";
    }
}
