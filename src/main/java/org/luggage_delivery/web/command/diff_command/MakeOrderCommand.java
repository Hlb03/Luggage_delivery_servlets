package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 18:06
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.dao.dao_interfaces.RouteDAO;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.service.service_impls.RouteServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.luggage_delivery.util.PaginationUtil.getDefaultPaginationData;

public class MakeOrderCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
            List<Route> routes = routeService.getAllRoutes(getDefaultPaginationData(req)[0], "distance", "asc");

            session.close();

            req.setAttribute("allRoutes", routes);

        } catch (DataBaseException e) {
            e.printStackTrace();
        }
            return "WEB-INF/jsp/make-order.jsp";
        }
    }
