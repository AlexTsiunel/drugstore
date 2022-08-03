package com.company.app.controller.command;

import com.company.app.model.dto.ClientDto;
import com.company.app.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;

public class GetClientCommand implements Command{
    private ClientService clientService;

    public GetClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        ClientDto client = clientService.getById(id);
        req.setAttribute("client", client);
        return "jsp/client.jsp";
    }
}
