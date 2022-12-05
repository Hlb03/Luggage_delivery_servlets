package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 15:37
*/

import org.junit.jupiter.api.Test;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.Role;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.entity.Tariff;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class UpdateUtilTest {


    @Test
    void updateRoleParamsTest() {
        Role role1 = mock(Role.class);
        Role role2 = mock(Role.class);
        role1.setRoleName("ONE");
        role2.setRoleName("TWO");

        UpdateUtil.updateRoleParams(role1, role2);
        verify(role1, times(1)).setRoleName(role2.getRoleName());
        verify(role2, times(2)).getRoleName();
    }

    @Test
    void updateTariffParamsTest() {
        Tariff tariff1 = mock(Tariff.class);
        Tariff tariff2 = mock(Tariff.class);
        tariff1.setType("TEST1");
        tariff1.setPrice(new BigDecimal("20"));
        tariff2.setType("TEST2");
        tariff2.setPrice(new BigDecimal("5"));

        UpdateUtil.updateTariffParams(tariff1, tariff2);
        verify(tariff1, times(1)).setType("TEST1");
        verify(tariff1, times(1)).setPrice(new BigDecimal("20"));
        verify(tariff2, times(1)).setType("TEST2");
        verify(tariff2, times(1)).setPrice(new BigDecimal("5"));
        verify(tariff2, times(1)).getType();
        verify(tariff2, times(1)).getPrice();
    }

    @Test
    void updateDeliveryStatusParamsTest() {
        DeliveryStatus status1 = mock(DeliveryStatus.class);
        DeliveryStatus status2 = mock(DeliveryStatus.class);
        status1.setStatusName("FIRST");
        status2.setStatusName("SECOND");

        UpdateUtil.updateDeliveryStatusParams(status1, status2);
        verify(status1, times(1)).setStatusName("FIRST");
        verify(status2, times(1)).setStatusName("SECOND");
        verify(status2, times(1)).getStatusName();
    }

    @Test
    void updateRoutesParamsTest() {
        Route route1 = mock(Route.class);
        Route route2 = mock(Route.class);
        route1.setStartPoint("START1");
        route1.setDestinationPoint("END1");
        route2.setStartPoint("START2");
        route2.setStartPoint("END2");

        UpdateUtil.updateRoutesParams(route1, route2);
        verify(route1, times(1)).setStartPoint("START1");
        verify(route1, times(1)).setDestinationPoint("END1");
        verify(route2, times(1)).setStartPoint("START2");
        verify(route2, times(1)).getStartPoint();
        verify(route2, times(1)).getDestinationPoint();
    }
}
