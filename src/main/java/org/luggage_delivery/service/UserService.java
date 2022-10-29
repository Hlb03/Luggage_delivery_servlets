package org.luggage_delivery.service;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 11:23
*/

import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;

import java.util.List;

public interface UserService {
    void addUser(User user) throws DataBaseException;
    User getById(int id) throws DataBaseException;
    User getByLogin(String userLogin) throws DataBaseException;
    List<User> getAllUsers() throws DataBaseException;
    void updateUser(int id, User user) throws DataBaseException;
    void deleteUser(User user) throws DataBaseException;
}
