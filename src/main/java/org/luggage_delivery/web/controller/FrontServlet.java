package org.luggage_delivery.web.controller;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:16
*/

import org.luggage_delivery.web.command.Command;
import org.luggage_delivery.web.command.container.CommandContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Luggage-delivery")
public class FrontServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(FrontServlet.class);

    private CommandContainer container;

    @Override
    public void init(ServletConfig config) throws ServletException {
        container = (CommandContainer) config.getServletContext().getAttribute("commandContainer");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getRequestURL());

        String url = getUrl(req, resp);
        LOG.debug("Forward to " + url);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN POST METHOD WITH URL " + req.getRequestURL());

        String url = getUrl(req, resp);
        LOG.debug("Redirect to " + url);
        resp.sendRedirect(url);
    }

    String getUrl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmdName = req.getParameter("cmd");
        LOG.debug(cmdName);
        Command command = container.getCommand(cmdName);

        if (req.getParameter("priceCalculateButton") != null)
            command = container.getCommand("calculate-price");
        else if (command == null)
            command = container.getCommand("404");

        return command.executeCommand(req, resp);
    }
}
