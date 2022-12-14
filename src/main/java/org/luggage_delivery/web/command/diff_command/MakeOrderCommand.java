package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 18:06
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
import java.util.List;

public class MakeOrderCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
            long totalAmountOfRouts = routeService.getRoutesAmount();
            List<Route> routes = routeService.getAllRoutes(1, (int) totalAmountOfRouts, "distance", "asc");

            session.close();

            req.setAttribute("allRoutes", routes);

            req.setAttribute("sizeParam", req.getParameter("size"));
            req.setAttribute("typeParam", req.getParameter("type"));
            req.setAttribute("weightParam", req.getParameter("weight"));
            req.setAttribute("delDateParam", req.getParameter("del-date"));
            req.setAttribute("addressParam", req.getParameter("address"));
            req.setAttribute("routeParam", req.getParameter("route"));
            req.setAttribute("optionParam", req.getParameter("option"));
            req.setAttribute("totalPrice", req.getParameter("totalPrice"));

        } catch (DataBaseException e) {
            e.printStackTrace();
        }
            return "WEB-INF/jsp/make-order.jsp";
        }
    }
