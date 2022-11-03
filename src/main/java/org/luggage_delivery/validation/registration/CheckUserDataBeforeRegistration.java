package org.luggage_delivery.validation.registration;
/*
  User: admin
  Cur_date: 03.11.2022
  Cur_time: 15:08
*/

import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.UserAlreadyExistsException;

public class CheckUserDataBeforeRegistration {
    public static void checkUserEmailBeforeAdding(User user, String login) throws UserAlreadyExistsException {
        if (user != null)
            throw new UserAlreadyExistsException("User with login " + login + " already exists. Failed to registrate...");
    }
}
