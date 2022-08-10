package com.company.app.controller.command.login;

import com.company.app.controller.command.Command;
import com.company.app.model.dto.ClientDto;
import com.company.app.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private final ClientService clientService;

    public LoginCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ClientDto client = clientService.login(email, password);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("client", client);
        return "index.jsp";
    }
}
