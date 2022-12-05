package org.luggage_delivery.web.contoller;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 16:24
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.luggage_delivery.web.command.Command;
import org.luggage_delivery.web.command.container.CommandContainer;
import org.luggage_delivery.web.command.diff_command.AddNewUserCommand;
import org.luggage_delivery.web.controller.FrontServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class FrontServletTest {

    private final FrontServlet servlet = new FrontServlet();
    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    private final Command command = mock(AddNewUserCommand.class);
    private final ServletConfig config = mock(ServletConfig.class);
    private final ServletContext context = mock(ServletContext.class);

    private final CommandContainer container = mock(CommandContainer.class);

    @BeforeEach
    void init() throws ServletException {
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("commandContainer")).thenReturn(container);
        when(req.getRequestURL()).thenReturn(new StringBuffer("fromTestUrl"));
        when(req.getParameter("cmd")).thenReturn("testCase");
        when(container.getCommand("testCase")).thenReturn(command);

        servlet.init(config);
    }

    @Test
    void doGetTest() throws ServletException, IOException {
        when(command.executeCommand(req, resp)).thenReturn("someUrl");
        when(req.getRequestDispatcher("someUrl")).thenReturn(dispatcher);

        servlet.doGet(req, resp);

        verify(req, times(1)).getParameter("cmd");
        verify(req, times(1)).getParameter("priceCalculateButton");
        verify(req, times(1)).getRequestDispatcher("someUrl");
        verify(dispatcher, times(1)).forward(req, resp);
    }

    @Test
    void doPostTest() throws ServletException, IOException {
        when(command.executeCommand(req, resp)).thenReturn("anotherUrl");

        servlet.doPost(req, resp);

        verify(req, times(1)).getParameter("cmd");
        verify(req, times(1)).getParameter("priceCalculateButton");
        verify(resp, times(1)).sendRedirect("anotherUrl");
    }
}
