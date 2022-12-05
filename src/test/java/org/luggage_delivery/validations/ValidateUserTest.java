package org.luggage_delivery.validations;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 14:15
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.IncorrectPasswordException;
import org.luggage_delivery.exceptions.UserNotExistsException;
import org.luggage_delivery.validation.authorize.ValidateUser;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUserTest {

    @Test
    void validateUserDataNotExistsTest() {
        Exception ex = assertThrows(UserNotExistsException.class,
                () -> ValidateUser.validateUserData(null, "test@case.com", null));

        assertEquals("User with login test@case.com do not exists", ex.getMessage());
    }

    @Test
    void validateUserDataIncorrectPassword() {
        User user = new User();
        user.setPassword("12345");

        Exception ex = assertThrows(IncorrectPasswordException.class,
                () -> ValidateUser.validateUserData(user, "another@test.com", "11111"));

        assertEquals("Incorrect password inputted", ex.getMessage());
    }
}
