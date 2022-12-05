package org.luggage_delivery.validation.authorize;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 21:49
*/

import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.IncorrectPasswordException;
import org.luggage_delivery.exceptions.UserNotExistsException;

public class ValidateUser {
    public static void validateUserData(User user, String login, String password)
                                        throws UserNotExistsException, IncorrectPasswordException {
        if (user == null)
            throw new UserNotExistsException("User with login " + login + " do not exists");

        if (!user.getPassword().equals(password))
            throw new IncorrectPasswordException("Incorrect password inputted");
    }
}
