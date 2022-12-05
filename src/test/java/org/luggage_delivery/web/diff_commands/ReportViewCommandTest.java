package org.luggage_delivery.web.diff_commands;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 17:50
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.web.command.diff_command.manager_commands.ReportViewCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReportViewCommandTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);

    private final ReportViewCommand command = new ReportViewCommand();

    @Test
    void executeCommandTest() throws ServletException, IOException {
        assertEquals("WEB-INF/jsp/manager/create-report.jsp", command.executeCommand(req, resp));
    }
}
