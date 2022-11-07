package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 06.11.2022
  Cur_time: 15:28
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class BalanceReplenishmentCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));

        try {
            session.beginTransaction();

            User user = userService.getById((int) req.getSession().getAttribute("user"));
            user.setBalance(user.getBalance().add(new BigDecimal(req.getParameter("balance"))));

            userService.updateUser(user.getId(), user);

            session.getTransaction().commit();
            session.close();
        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        return "Luggage-delivery?cmd=user-room";
    }
}
