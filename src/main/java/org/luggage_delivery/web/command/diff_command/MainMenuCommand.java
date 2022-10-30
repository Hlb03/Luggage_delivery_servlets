package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 21.10.2022
  Cur_time: 21:20
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.dao.dao_implementations.TariffDAOImpl;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.service.TariffService;
import org.luggage_delivery.service.service_impls.RouteServiceImpl;
import org.luggage_delivery.service.service_impls.TariffServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.luggage_delivery.util.PaginationUtil.getDefaultPaginationData;

public class MainMenuCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        try {

            String tariffDir = req.getParameter("tariffDir");
            String row = req.getParameter("row");
            String routeDir = req.getParameter("routeDir");
            String col = req.getParameter("col");

            if (req.getParameter("tariff-change") == null || row == null) {
                if (row == null)
                    row = "id";

                if (tariffDir == null || tariffDir.equals("asc"))
                    tariffDir = "desc";
                else tariffDir = "asc";
            }

            if (req.getParameter("route-change") == null || col == null) {
                if (col == null)
                    col = "distance";

                if (routeDir == null || routeDir.equals("asc"))
                    routeDir = "desc";
                else routeDir = "asc";
            }

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            TariffService tariffService = new TariffServiceImpl(new TariffDAOImpl(session));
            RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
//            int totalAmountOfRoutes = (int) routeService.getRoutesAmount();
            List<Tariff> tariffs = tariffService.getTariffs(row, tariffDir);

            int[] paginationSetting = getDefaultPaginationData(req, (int) routeService.getRoutesAmount());

            List<Route> routes = routeService.getAllRoutes(paginationSetting[0], paginationSetting[1], col, routeDir);

            System.out.println("TOTALLY NEEDED " + paginationSetting[2] + " TO DISPLAY " + paginationSetting[1] + " ROUTES");
            session.getTransaction().commit();
            session.close();

            System.out.println(routes);
            req.setAttribute("allTariffs", tariffs);
            req.setAttribute("allRoutes", routes);

            req.setAttribute("tariffDir", tariffDir);
            req.setAttribute("routeDir", routeDir);
            req.setAttribute("totalPages", paginationSetting[2]);
            req.setAttribute("currentPage", paginationSetting[0]);

            req.setAttribute("row", row);
            req.setAttribute("col", col);
        } catch (DataBaseException e) {
            e.printStackTrace();
        }

        return "WEB-INF/jsp/main-page.jsp";
    }
}