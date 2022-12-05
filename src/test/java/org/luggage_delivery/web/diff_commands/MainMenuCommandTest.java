package org.luggage_delivery.web.diff_commands;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 17:42
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.web.command.diff_command.MainMenuCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class MainMenuCommandTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);

    private final MainMenuCommand command = new MainMenuCommand();

    @Test
    void executeCommandTest() throws ServletException {
        when(req.getParameter("tariffDir")).thenReturn("asc");
        when(req.getParameter("row")).thenReturn("type");
        when(req.getParameter("routeDir")).thenReturn("desc");
        when(req.getParameter("col")).thenReturn("test");

        command.executeCommand(req, resp);
    }
}
