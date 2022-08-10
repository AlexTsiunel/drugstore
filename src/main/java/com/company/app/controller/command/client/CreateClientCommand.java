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
        String idStr = req.getParameter("id");
        Long id = null;
        if (idStr != null){
            id = Long.parseLong(req.getParameter("id"));
        }
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ClientDto client = new ClientDto();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setPassword(password);
        ClientDto newClient = clientService.saveOrUpdate(client);
        req.setAttribute("client", newClient);
        if (id == null) {
            req.setAttribute("message", "New client was created");
        } else {
            req.setAttribute("message", "Client was edited");
        }
        return "jsp/client/client.jsp";
    }
}
