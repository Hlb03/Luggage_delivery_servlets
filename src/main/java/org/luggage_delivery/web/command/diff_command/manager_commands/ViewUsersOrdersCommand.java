package org.luggage_delivery.web.command.diff_command.manager_commands;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 10:37
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
import java.util.List;

public class ViewUsersOrdersCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN MANAGER COMMAND");

        Session session = HibernateUtil.getSessionFactory().openSession();
        DeliveryService deliveryService = new DeliveryServiceImpl(new DeliveryDAOImpl(session));
        DeliveryStatusService deliveryStatusService = new DeliveryStatusServiceImpl(new DeliveryStatusDAOImpl(session));

        try {
            DeliveryStatus status = deliveryStatusService.getStatusByName("PROCESSING");
            List<Delivery> deliveryWithProcessingStatus = deliveryService.getDeliveryByStatus(status);

            System.out.println("DELIVERY WITH STATUS 'PROCESSING' " + deliveryWithProcessingStatus);
            req.setAttribute("processDeliveries", deliveryWithProcessingStatus);
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        session.close();

        return "WEB-INF/jsp/manager/all-processing-deliveries.jsp";
    }
}
