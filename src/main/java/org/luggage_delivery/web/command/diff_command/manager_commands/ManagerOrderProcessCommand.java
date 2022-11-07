package org.luggage_delivery.web.command.diff_command.manager_commands;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 13:43
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryDAOImpl;
import org.luggage_delivery.dao.dao_implementations.DeliveryStatusDAOImpl;
import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryService;
import org.luggage_delivery.service.DeliveryStatusService;
import org.luggage_delivery.service.service_impls.DeliveryServiceImpl;
import org.luggage_delivery.service.service_impls.DeliveryStatusServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManagerOrderProcessCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN MANAGER PROCESS COMMAND");
        Session session = HibernateUtil.getSessionFactory().openSession();
        DeliveryStatusService deliveryStatusService = new DeliveryStatusServiceImpl(new DeliveryStatusDAOImpl(session));
        DeliveryService deliveryService = new DeliveryServiceImpl(new DeliveryDAOImpl(session));

        try {
            session.beginTransaction();

            Delivery delivery = deliveryService.getDeliveryById(Integer.parseInt(req.getParameter("orderId")));
            DeliveryStatus status;
            if ("approve".equals(req.getParameter("processOrder")))
                status = deliveryStatusService.getStatusByName("PAY");
            else status = deliveryStatusService.getStatusByName("DECLINED");

            delivery.setDeliveryStatus(status);
            deliveryService.updateDelivery(delivery.getId(), delivery);

            session.getTransaction().commit();
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }
        session.close();

        return "Luggage-delivery?cmd=manager-room";
    }
}
