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
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.util.PaginationUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MakeOrderCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RouteDAO routeDAO = new RouteDAOImpl(session);
        List<Route> routes = routeDAO.getAllRoutes(PaginationUtil.getDefaultPaginationData(req)[0], "distance", "asc");

        session.close();

        req.setAttribute("allRoutes", routes);

        return "WEB-INF/jsp/make-order.jsp";
    }
}
