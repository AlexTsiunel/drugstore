package com.company.app.controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String command = req.getParameter("command");
        if (requiresAuthorization(command)) {
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("client") == null) {
                req.setAttribute("message", "Requested page requires authorization");
                req.getRequestDispatcher("jsp/loginForm.jsp").forward(req, res);
                return;
            }
        }
        chain.doFilter(req, res);
    }

    private boolean requiresAuthorization(String command) {
        if(command == null){
            return false;
        }
        switch (command) {
            case "clients":
            case "orders":
                return true;
        }
        return false;
    }
}
