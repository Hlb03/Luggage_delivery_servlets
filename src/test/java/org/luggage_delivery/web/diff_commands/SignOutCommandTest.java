package org.luggage_delivery.web.diff_commands;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 16:58
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.web.command.diff_command.SignOutCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SignOutCommandTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    private final SignOutCommand command = new SignOutCommand();

    @Test
    void executeCommandTest() throws ServletException, IOException {
        when(req.getSession()).thenReturn(session);

        command.executeCommand(req, resp);

        verify(req, times(1)).getSession();
        verify(session, times(1)).invalidate();
    }
}
