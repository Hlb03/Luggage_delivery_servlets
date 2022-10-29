package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 25.10.2022
  Cur_time: 7:14
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryStatusDAOImpl;
import org.luggage_delivery.dao.dao_implementations.TariffDAOImpl;
import org.luggage_delivery.dao.dao_interfaces.DeliveryStatusDAO;
import org.luggage_delivery.dao.dao_interfaces.TariffDAO;
import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderProcessCommand extends Command {
    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        System.out.println(req.getParameter("luggage-size") + " SIZE OF PRODUCT");
        System.out.println(req.getParameter("luggage-type") + " TYPE OF PRODUCT");
        System.out.println(req.getParameter("luggage-weight") + " WEIGHT OF PRODUCT");
        System.out.println(req.getParameter("routeId") + " ROUTE ID OF PRODUCT");
        System.out.println(req.getParameter("luggage-del-date") + " DELIVERY DATE OF PRODUCT");
        System.out.println(req.getParameter("delivery-address") + " DELIVERY ADDRESS OF PRODUCT");
        System.out.println(req.getParameter("empty") + " ADDITIONAL OPTION OF PRODUCT");
        System.out.println(req.getParameter("fragile") + " ADDITIONAL OPTION OF PRODUCT");
        System.out.println(req.getParameter("radioResult") + " ADDITIONAL CHECK WITH JS OPTION OF PRODUCT");


//        Session session = HibernateUtil.getSessionFactory().openSession();
//        TariffDAO tariffDAO = new TariffDAOImpl(session);
//        DeliveryStatusDAO deliveryStatusDAO = new DeliveryStatusDAOImpl(session);

//        List<Tariff> tariffs = tariffDAO.getAllTariffs("type", "asc");

//        session.close();
        System.out.println("HELLO WORLD IN ORDER PROCESS COMMAND!");
        return "Luggage-delivery?cmd=exit";
    }
}
