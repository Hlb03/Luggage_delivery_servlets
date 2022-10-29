package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 18:44
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_interfaces.UserDAO;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.util.UpdateUtil;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Session session;

    public UserDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewUser(User user) {
        session.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return session.createQuery("SELECT u FROM User u", User.class).list();
    }

    @Override
    public User getById(int id) {
        return session.get(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        return session.createQuery("SELECT u FROM User u where u.login = :login", User.class)
                .setParameter("login", login).uniqueResult();
    }

    @Override
    public void updateUser(int id, User user) {
        User user1 = getById(id);
        UpdateUtil.updateUserParams(user1, user);
        session.update(user1);
    }

    @Override
    public void deleteUser(User user) {
        session.createQuery("DELETE FROM User u WHERE u.firstName = :firstName AND u.lastName = :lastName" +
                " AND u.login = :login AND u.balance = :balance AND u.role = :role")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("login", user.getLogin())
                .setParameter("balance", user.getBalance())
                .setParameter("role", user.getRole())
                .executeUpdate();
    }
}
