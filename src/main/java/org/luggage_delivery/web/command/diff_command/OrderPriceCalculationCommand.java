package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 01.11.2022
  Cur_time: 10:32
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.entity.Delivery;
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
import java.math.BigDecimal;

import static org.luggage_delivery.util.PriceCalculationUtil.calculateGeneralPrice;

public class OrderPriceCalculationCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
        Delivery delivery = new Delivery();

        try {
            Route route = routeService.getById(Integer.parseInt(req.getParameter("routeId")));
            delivery.setSize(new BigDecimal(req.getParameter("luggage-size")));
            delivery.setWeight(new BigDecimal(req.getParameter("luggage-weight")));
            delivery.setRoute(route);

            delivery.setTotalPrice(calculateGeneralPrice(delivery, req.getParameter("option")));

            System.out.println("TOTAL PRICE IS - " + delivery.getTotalPrice());
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }


        session.close();
        return "Luggage-delivery?cmd=make-order&size=" + req.getParameter("luggage-size") +
                "&type=" + req.getParameter("luggage-type") + "&weight=" + req.getParameter("luggage-weight")
                + "&route=" + req.getParameter("routeId") + "&del-date=" + req.getParameter("luggage-del-date") +
                "&address=" + req.getParameter("delivery-address") + "&option=" + req.getParameter("option") +
                "&totalPrice=" + delivery.getTotalPrice();
    }
}
