package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:20
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.dao.dao_implementations.TariffDAOImpl;
import org.luggage_delivery.dao.dao_interfaces.RouteDAO;
import org.luggage_delivery.dao.dao_interfaces.TariffDAO;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.util.PaginationUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.luggage_delivery.util.PaginationUtil.getDefaultPaginationData;

public class MainMenuCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        int[] paginationData = PaginationUtil.getDefaultPaginationData(req);


        String tariffDir = req.getParameter("tariffDir");
        String row = req.getParameter("row");
        String routeDir = req.getParameter("routeDir");
        String col = req.getParameter("col");

        if (row == null)
            row = "id";

        if (tariffDir == null || tariffDir.equals("asc"))
            tariffDir = "desc";
        else tariffDir = "asc";

        if (col == null)
            col = "distance";

        if (routeDir == null || routeDir.equals("asc"))
            routeDir = "desc";
        else routeDir = "asc";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        TariffDAO tariffDAO = new TariffDAOImpl(session);
        RouteDAO routeDAO = new RouteDAOImpl(session);
        List<Tariff> tariffs = tariffDAO.getAllTariffs(row, tariffDir);
        List<Route> routes = routeDAO.getAllRoutes(PaginationUtil.getDefaultPaginationData(req)[0], col, routeDir);
        System.out.println(routeDAO.getAmountOfRoutes() + " AMOUNT OF ROUTES");

        session.getTransaction().commit();
        session.close();

        System.out.println(routes);
        req.setAttribute("allTariffs", tariffs);
        req.setAttribute("allRoutes", routes);

        req.setAttribute("tariffDir",  tariffDir);
        req.setAttribute("routeDir", routeDir);

        return "WEB-INF/jsp/main-page.jsp";
    }
}