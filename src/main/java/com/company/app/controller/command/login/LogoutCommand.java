package com.company.app.controller.command.login;

import com.company.app.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        req.getSession().removeAttribute("client");
        return "index.jsp";
    }
}
