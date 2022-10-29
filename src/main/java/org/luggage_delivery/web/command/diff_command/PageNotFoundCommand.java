package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 13:37
*/

import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageNotFoundCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        return "WEB-INF/error_pages/404.html";
    }
}
