package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 21:21
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.exceptions.IncorrectPasswordException;
import org.luggage_delivery.exceptions.UserNotExistsException;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.luggage_delivery.validation.authorize.ValidateUser.validateUserData;

public class AuthorizationCommand extends Command {

    private final static Logger LOG = LoggerFactory.getLogger(AuthorizationCommand.class);

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailInput = req.getParameter("userEmail");
        String passInput = req.getParameter("userPass");

        Session session = HibernateUtil.getSessionFactory().openSession();
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));

        try {
            User user = userService.getByLogin(emailInput);
            validateUserData(user, emailInput, passInput);

            session.close();
            req.getSession().setAttribute("user", user.getId());
            req.getSession().setAttribute("userRole", user.getRole().getRoleName());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (UserNotExistsException e) {
            LOG.debug("User not exists");
            req.setAttribute("emailException", "Incorrect email");
            req.getRequestDispatcher("WEB-INF/jsp/sign-in.jsp").forward(req, resp);
        } catch (IncorrectPasswordException e) {
            LOG.debug("Incorrect password inputted");
            req.setAttribute("userEmail", emailInput);
            req.setAttribute("passwordException", "Incorrect password");
            req.getRequestDispatcher("WEB-INF/jsp/sign-in.jsp").forward(req, resp);
        }


        return "Luggage-delivery";
    }
}
