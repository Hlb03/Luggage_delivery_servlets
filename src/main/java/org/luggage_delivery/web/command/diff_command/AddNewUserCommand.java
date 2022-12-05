package org.luggage_delivery.web.command.diff_command;
/*
  User: admin
  Cur_date: 03.11.2022
  Cur_time: 14:27
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.RoleDAOImpl;
import org.luggage_delivery.dao.dao_implementations.UserDAOImpl;
import org.luggage_delivery.entity.Role;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.exceptions.UserAlreadyExistsException;
import org.luggage_delivery.service.RoleService;
import org.luggage_delivery.service.UserService;
import org.luggage_delivery.service.service_impls.RoleServiceImpl;
import org.luggage_delivery.service.service_impls.UserServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.validation.registration.CheckUserDataBeforeRegistration;
import org.luggage_delivery.web.command.Command;
import org.luggage_delivery.web.command.diff_command.manager_commands.ViewUsersOrdersCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddNewUserCommand extends Command {

    private final static Logger LOG = LoggerFactory.getLogger(AddNewUserCommand.class);

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        RoleService roleService = new RoleServiceImpl(new RoleDAOImpl(session));
        UserService userService = new UserServiceImpl(new UserDAOImpl(session));

        try {
            CheckUserDataBeforeRegistration.checkUserEmailBeforeAdding(
                    userService.getByLogin(req.getParameter("email")), req.getParameter("email"));

            Role role = roleService.getByName("USER");
            User user = new User();
            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setLogin(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            user.setBalance(new BigDecimal("0"));
            user.setRole(role);

            userService.addUser(user);
        } catch (UserAlreadyExistsException ex) {
            LOG.debug(ex.getMessage());
            req.setAttribute("firstName", req.getParameter("firstName"));
            req.setAttribute("lastName", req.getParameter("lastName"));
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("pas1", req.getParameter("password"));
            req.setAttribute("pas2", req.getParameter("password2"));
            req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
        }catch (DataBaseException ex) {
            ex.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();

        return "Luggage-delivery?cmd=authorize";
    }
}
