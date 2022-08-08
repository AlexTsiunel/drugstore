package com.company.app.controller.command.client;

import com.company.app.controller.command.Command;
import com.company.app.dao.ClientDao;
import com.company.app.model.dto.ClientDto;
import com.company.app.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;

public class CreateClientCommand implements Command {
    private final ClientService clientService;

    public CreateClientCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ClientDto client = new ClientDto();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setPassword(password);
        ClientDto newClient = clientService.saveOrUpdate(client);
        req.setAttribute("client", newClient);
        req.setAttribute("message", "New client was created");
        return "jsp/client/client.jsp";
    }
}
