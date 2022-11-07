package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 7:40
*/

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryDAOImpl;
import org.luggage_delivery.dao.dao_implementations.DeliveryStatusDAOImpl;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryService;
import org.luggage_delivery.service.DeliveryStatusService;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.DeliveryServiceImpl;
import org.luggage_delivery.service.service_impls.DeliveryStatusServiceImpl;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PayOrderCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN PAY ORDER COMMAND");
        Session session = HibernateUtil.getSessionFactory().openSession();
        DeliveryService deliveryService = new DeliveryServiceImpl(new DeliveryDAOImpl(session));
        DeliveryStatusService deliveryStatusService = new DeliveryStatusServiceImpl(new DeliveryStatusDAOImpl(session));
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));

        try {
            session.beginTransaction();
            DeliveryStatus status = deliveryStatusService.getStatusByName("IN PROGRESS");
            Delivery delivery = deliveryService.getDeliveryById(Integer.parseInt(req.getParameter("orderId")));
            User user = userService.getById(delivery.getUser().getId());

            user.setBalance(user.getBalance().subtract(delivery.getTotalPrice()));
            delivery.setDeliveryStatus(status);

            userService.updateUser(user.getId(), user);
            deliveryService.updateDelivery(delivery.getId(), delivery);

            //            System.out.println(status);
//            System.out.println(user);
//            System.out.println(delivery);

            session.getTransaction().commit();
            session.close();
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        return "Luggage-delivery?cmd=user-room";
    }
}
