package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.User;

import java.util.List;

public interface UserDAO {
    void addNewUser(User user);
    List<User> getAllUsers();
    User getById(int id);
    User getByLogin(String login);
    void updateUser(int id, User user);
    void deleteUser(User user);
}
