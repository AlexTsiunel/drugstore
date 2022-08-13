package com.company.app.controller.command;

import com.company.app.controller.command.client.*;
import com.company.app.controller.command.drug.AllDrugsCommand;
import com.company.app.controller.command.drug.CreateDrugCommand;
import com.company.app.controller.command.drug.CreateDrugFormCommand;
import com.company.app.controller.command.drug.GetDrugCommand;
import com.company.app.controller.command.login.LoginCommand;
import com.company.app.controller.command.login.LoginFormCommand;
import com.company.app.controller.command.order.AllOrdersCommand;
import com.company.app.controller.command.order.GetOrderCommand;
import com.company.app.controller.command.pharmacist.AllPharmacistCommand;
import com.company.app.controller.command.pharmacist.GetPharmacistCommand;
import com.company.app.service.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Command> commands;
    private static class CommandFactoryHolder{
        public static final CommandFactory HOLDER_INSTANCE = new CommandFactory();

    }

    private CommandFactory() {
        this.commands = new HashMap<>();
        commands.put("login_form", new LoginFormCommand());
        commands.put("login", new LoginCommand(ServiceFactory.getInstance().getService(ClientService.class)));

        commands.put("add_to_cart", new AddToCartCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("remove_from_cart", new RemoveFromCartCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("show_cart", new ShowCartCommand(ServiceFactory.getInstance().getService(DrugService.class)));

        commands.put("drugs", new AllDrugsCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("drug", new GetDrugCommand(ServiceFactory.getInstance().getService(DrugService.class)));
        commands.put("create_drug_form", new CreateDrugFormCommand());
        commands.put("create_drug", new CreateDrugCommand(ServiceFactory.getInstance().getService(DrugService.class)));

        commands.put("clients", new AllClientsCommand(ServiceFactory.getInstance().getService(ClientService.class)));
        commands.put("client", new GetClientCommand(ServiceFactory.getInstance().getService(ClientService.class)));
        commands.put("create_client_form", new CreateClientFormCommand());
        commands.put("edit_client_form", new EditClientFormCommand(ServiceFactory.getInstance().getService(ClientService.class)));
        commands.put("create_client", new CreateClientCommand(ServiceFactory.getInstance().getService(ClientService.class)));

        commands.put("orders", new AllOrdersCommand(ServiceFactory.getInstance().getService(OrderService.class)));
        commands.put("order", new GetOrderCommand(ServiceFactory.getInstance().getService(OrderService.class)));
        commands.put("create_order", new CreateOrderCommand(ServiceFactory.getInstance().getService(OrderService.class)));


        commands.put("pharmacists", new AllPharmacistCommand(ServiceFactory.getInstance().getService(PharmacistService.class)));
        commands.put("pharmacist", new GetPharmacistCommand(ServiceFactory.getInstance().getService(PharmacistService.class)));

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
