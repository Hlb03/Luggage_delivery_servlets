package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 30.10.2022
  Cur_time: 9:29
*/

import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOutCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        return "Luggage-delivery";
    }
}
