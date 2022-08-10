package com.company.app.controller.command.login;

import com.company.app.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LoginFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/loginForm.jsp";
    }
}
