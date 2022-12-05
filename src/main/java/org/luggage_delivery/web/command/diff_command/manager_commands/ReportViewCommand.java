package org.luggage_delivery.web.command.diff_command.manager_commands;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 14:13
*/

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.service.service_impls.RouteServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReportViewCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));

        try {
            List<Route> allRoutes =
                    routeService.getAllRoutes(1, (int) routeService.getRoutesAmount(), "distance", "asc");

            req.setAttribute("downloadPdf", req.getParameter("downloadPdf"));
            req.setAttribute("routes", allRoutes);
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        session.close();
        return "WEB-INF/jsp/manager/create-report.jsp";
    }
}
