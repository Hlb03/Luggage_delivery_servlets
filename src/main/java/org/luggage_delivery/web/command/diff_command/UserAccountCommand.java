package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 30.10.2022
  Cur_time: 9:53
*/

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
import java.util.ResourceBundle;

import static org.luggage_delivery.util.PaginationUtil.getDefaultPaginationData;

public class UserAccountCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));

        try {
            int[] paginationSettings = getDefaultPaginationData(req, (int) routeService.getRoutesAmount());

            List<Route> allRoutes =
                    routeService.getAllRoutes(paginationSettings[0], paginationSettings[1], "distance", "asc");
            List<Route> route =
                    routeService.getAllRoutes(2, paginationSettings[1], "distance", "asc");

            req.setAttribute("allRoutes", allRoutes);
            req.setAttribute("routes", route);
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        session.close();

        return "WEB-INF/jsp/user-account.jsp";
    }
}
