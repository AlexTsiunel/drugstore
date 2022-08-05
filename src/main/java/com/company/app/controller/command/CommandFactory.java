package com.company.app.controller.command;

import com.company.app.controller.command.client.AllClientsCommand;
import com.company.app.controller.command.client.GetClientCommand;
import com.company.app.controller.command.drug.AllDrugsCommand;
import com.company.app.controller.command.drug.GetDrugCommand;
import com.company.app.service.ClientService;
import com.company.app.service.DrugService;
import com.company.app.service.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Command> commands;
    private static class CommandFactoryHolder{
        public static final CommandFactory HOLDER_INSTANCE = new CommandFactory();

    }

    private CommandFactory() {
        this.commands = new HashMap<>();
        commands.put("drugs", new AllDrugsCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("drug", new GetDrugCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("clients", new AllClientsCommand(ServiceFactory.getInstance().getService(ClientService.class)));
        commands.put("client", new GetClientCommand(ServiceFactory.getInstance().getService(ClientService.class)));

        commands.put("error", new ErrorCommand());
    }

    public Command getCommand(String command) {
        Command commandInstance = commands.get(command);
        if (commandInstance == null) {
            commandInstance = commands.get("error");
        }
        return commandInstance;
    }

    public static CommandFactory getInstance() {
        return CommandFactoryHolder.HOLDER_INSTANCE;
    }
}
