package org.luggage_delivery.web.listener;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:22
*/

import org.luggage_delivery.web.command.diff_command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.luggage_delivery.web.command.Command;
import org.luggage_delivery.web.command.container.CommandContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private final static Logger LOG = LoggerFactory.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("Context initialization was started");
        ServletContext context = sce.getServletContext();
        initCommandContainer(context);
    }

    private void initCommandContainer(ServletContext context) {
        //init all created command and put 'em into container

        CommandContainer container = new CommandContainer();

        Command mainMenuCommand = new MainMenuCommand();
        Command makeOrderCommand = new MakeOrderCommand();
        Command authorizeCommand = new SignInFormCommand();
        Command pageNotFoundCommand = new PageNotFoundCommand();
        Command orderProcessCommand = new OrderProcessCommand();
        Command checkAuthorizationCommand = new AuthorizationCommand();
        Command userAccountCommand = new UserAccountCommand();
        Command priceCalculation = new OrderPriceCalculationCommand();
        Command logoutCommand = new SignOutCommand();
        Command registrationCommand = new RegistrationCommand();
        Command addNewUserCommand = new AddNewUserCommand();

        container.addCommand(null, mainMenuCommand);
        container.addCommand("", mainMenuCommand);
        container.addCommand("menu", mainMenuCommand);
        container.addCommand("make-order", makeOrderCommand);
        container.addCommand("order-process", orderProcessCommand);
        container.addCommand("calculate-price", priceCalculation);
        container.addCommand("authorize", authorizeCommand);
        container.addCommand("logout", logoutCommand);
        container.addCommand("user-check", checkAuthorizationCommand);
        container.addCommand("user-room", userAccountCommand);
        container.addCommand("404", pageNotFoundCommand);
        container.addCommand("registrate", registrationCommand);
        container.addCommand("addNewUser", addNewUserCommand);

        context.setAttribute("commandContainer", container);
        LOG.debug("Set attribute 'commandContainer' to context - " + container);
    }
}
