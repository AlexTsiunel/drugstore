package com.company.app.controller.command;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        String language = req.getParameter("language");
        HttpSession session = req.getSession();
        session.setAttribute("language", language);
        return "index.jsp";
    }
}
