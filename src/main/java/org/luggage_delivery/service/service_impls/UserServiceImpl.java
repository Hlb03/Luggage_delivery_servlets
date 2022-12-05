package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 11:25
*/

import org.luggage_delivery.dao.dao_interfaces.UserDAO;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void addUser(User user) throws DataBaseException {
        try {
            userDAO.addNewUser(user);
        } catch (Exception e) {
            throw new DataBaseException("Failed to add new user", e);
        }
    }

    @Override
    public User getById(int id) throws DataBaseException {
        User user;
        try {
            user = userDAO.getById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get user by id");
        }
        return user;
    }

    @Override
    public User getByLogin(String userLogin) throws DataBaseException {
        User user;
        try {
            user = userDAO.getByLogin(userLogin);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get user by login", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws DataBaseException {
        try {
            return userDAO.getAllUsers();
        } catch (Exception e) {
            throw new DataBaseException("Failed to get all users");
        }
    }

    @Override
    public void updateUser(int id, User user) throws DataBaseException {
        try {
            userDAO.updateUser(id, user);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update user", e);
        }
    }

    @Override
    public void deleteUser(User user) throws DataBaseException {
        try {
            userDAO.deleteUser(user);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete user", e);
        }
    }
}
