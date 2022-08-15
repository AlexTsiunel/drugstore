package com.company.app.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public class ShowHomeCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "index.jsp";
    }
}
