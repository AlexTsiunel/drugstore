package com.company.app.controller.command.client;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.ClientDto;
import com.company.app.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class AllClientsCommand implements Command {
    private final ClientService clientService;

    public AllClientsCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<ClientDto> clients = clientService.getAll();
        req.setAttribute("clients", clients);
        return "jsp/client/clients.jsp";
    }
}
