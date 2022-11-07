package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 30.10.2022
  Cur_time: 9:53
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryDAOImpl;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryService;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.DeliveryServiceImpl;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.luggage_delivery.util.PaginationUtil.getDefaultUserOrderPaginationData;

public class UserAccountCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));
        DeliveryService deliveryService = new DeliveryServiceImpl(new DeliveryDAOImpl(session));

        try {
            User user = userService.getById((int) req.getSession().getAttribute("user"));
//            System.out.println("TOTAL AMOUNT OF USER ORDERS: " + deliveryService.getUserDeliveriesAmount(user));

            int[] paginationUtil = getDefaultUserOrderPaginationData(req,
                            (int) deliveryService.getUserDeliveriesAmount(user));
            List<Delivery> userDeliveries = deliveryService.getAllUserDeliveries(user, paginationUtil[0], paginationUtil[1]);
            System.out.println("USER DELIVERIES " + userDeliveries);

            req.setAttribute("currentPage", paginationUtil[0]);
            req.setAttribute("totalPages", paginationUtil[2]);
            req.setAttribute("userBalance", user.getBalance());
            req.setAttribute("userDeliveries", userDeliveries);
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        session.close();

        return "WEB-INF/jsp/user-account.jsp";
    }
}
