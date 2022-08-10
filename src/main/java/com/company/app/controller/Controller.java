package com.company.app.controller;

import com.company.app.controller.command.Command;
import com.company.app.controller.command.CommandFactory;
import com.company.app.dao.connection.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void destroy() {
        DataSource.getInstance().close();
        super.destroy();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        Command commandInstance = CommandFactory.getInstance().getCommand(command);
        String path = commandInstance.execute(req);
//        !!!exceptions will be handled here!!!
//        String path;
//        try{
//            path = commandInstance.execute(req);
//
//        }catch (Exception e){
//            path = handleException(req, e);
//        }
        req.getRequestDispatcher(path).forward(req, resp);
    }

//    private static String handleException(HttpServletRequest req, Exception e) {
//        String page = "jsp/inValidError";
//        if(e instanceof  ValidationException validationException){
//            page = "jsp/validError";
//            req.setAttribute("errmsg", validationException.getValidMsg());
//        }
//        return page;
//    }
}
