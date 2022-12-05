package org.luggage_delivery.validations;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 14:27
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.UserAlreadyExistsException;
import org.luggage_delivery.validation.registration.CheckUserDataBeforeRegistration;

import static org.junit.jupiter.api.Assertions.*;

public class CheckUserDataBeforeRegistrationTest {

    @Test
    void checkUserEmailBeforeAddingTest() {
        Exception ex = assertThrows(UserAlreadyExistsException.class,
                () -> CheckUserDataBeforeRegistration.checkUserEmailBeforeAdding(new User(), "already@registered.com"));

        assertEquals("User with login already@registered.com already exists. Failed to registrate...",
                ex.getMessage());
    }
}
