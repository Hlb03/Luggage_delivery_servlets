package org.luggage_delivery.web.diff_commands;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 17:30
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.web.command.diff_command.MakeOrderCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MakeOrderCommandTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);
    private final HttpServletResponse resp = mock(HttpServletResponse.class);
    private final RouteService routeService = mock(RouteService.class);
    private final MakeOrderCommand command = new MakeOrderCommand();

    @Test
    void executeCommandTest() throws ServletException, DataBaseException {
        when(routeService.getRoutesAmount()).thenReturn(10L);
        when(req.getParameter("size")).thenReturn("30.4");
        when(req.getParameter("type")).thenReturn("TEST_TYPE");
        when(req.getParameter("weight")).thenReturn("2.2");
        when(req.getParameter("del_date")).thenReturn("2022-05-12");
        when(req.getParameter("address")).thenReturn("TEST_ADDRESS");
        when(req.getParameter("route")).thenReturn("3");
        when(req.getParameter("option")).thenReturn("FRAGILE");
        when(req.getParameter("totalPrice")).thenReturn("254.13");

        command.executeCommand(req, resp);

        verify(req, times(1)).getParameter("size");
        verify(req, times(1)).getParameter("type");
        verify(req, times(1)).getParameter("weight");
        verify(req, times(1)).getParameter("del-date");
        verify(req, times(1)).getParameter("address");
        verify(req, times(1)).getParameter("route");
        verify(req, times(1)).getParameter("option");
        verify(req, times(1)).getParameter("totalPrice");

    }
}
