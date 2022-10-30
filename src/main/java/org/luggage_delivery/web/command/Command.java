package org.luggage_delivery.web.command;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:05
*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public abstract String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
