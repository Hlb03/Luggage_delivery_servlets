package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 02.11.2022
  Cur_time: 12:39
*/

import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "WEB-INF/jsp/registration.jsp";
    }
}
