package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 25.10.2022
  Cur_time: 7:14
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryDAOImpl;
import org.luggage_delivery.dao.dao_implementations.DeliveryStatusDAOImpl;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.*;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryService;
import org.luggage_delivery.service.DeliveryStatusService;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.DeliveryServiceImpl;
import org.luggage_delivery.service.service_impls.DeliveryStatusServiceImpl;
import org.luggage_delivery.service.service_impls.RouteServiceImpl;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import static org.luggage_delivery.util.PriceCalculationUtil.calculateGeneralPrice;

public class OrderProcessCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        DeliveryStatusService deliveryService = new DeliveryStatusServiceImpl(new DeliveryStatusDAOImpl(session));
        RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));
        DeliveryService deliveryServ = new DeliveryServiceImpl(new DeliveryDAOImpl(session));


        try {
            DeliveryStatus status = deliveryService.getStatusByName("PROCESSING");
            Route route = routeService.getById(Integer.parseInt(req.getParameter("routeId")));
            User user = userService.getById((int) req.getSession().getAttribute("user"));

            Delivery delivery = new Delivery();
            delivery.setSize(new BigDecimal(req.getParameter("luggage-size")));
            delivery.setLuggageType(req.getParameter("luggage-type"));
            delivery.setWeight(new BigDecimal(req.getParameter("luggage-weight")));
            delivery.setStartDate(Date.valueOf(LocalDate.now()));
            delivery.setDeliveryDate(Date.valueOf(req.getParameter("luggage-del-date")));
            delivery.setDeliveryAddress(req.getParameter("delivery-address"));
            delivery.setRoute(route);
            delivery.setDeliveryStatus(status);
            delivery.setUser(user);
            delivery.setTotalPrice(calculateGeneralPrice(delivery, req.getParameter("option")));

            System.out.println("DELIVERY PARAMS " + delivery);
            deliveryServ.addDelivery(delivery);
            session.getTransaction().commit();
            session.close();

        } catch (DataBaseException e) {
            e.printStackTrace();
        }
        return "Luggage-delivery?cmd=user-room";
    }
}
