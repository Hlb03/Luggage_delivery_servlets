package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 17:14
*/

import org.luggage_delivery.entity.*;

public class UpdateUtil {
    public static void updateRoleParams(Role update, Role updater) {
        update.setRoleName(updater.getRoleName());
    }

    public static void updateTariffParams(Tariff update, Tariff updater) {
        update.setPrice(updater.getPrice());
        update.setType(updater.getType());
    }

    public static void updateUserParams(User update, User updater) {
        update.setFirstName(updater.getFirstName());
        update.setLastName(updater.getLastName());
        update.setLogin(updater.getLogin());
        update.setPassword(updater.getPassword());
        update.setRole(updater.getRole());
        update.setDelivery(updater.getDelivery());
    }

    public static void updateDeliveryStatusParams(DeliveryStatus update, DeliveryStatus updated) {
        update.setStatusName(updated.getStatusName());
    }

    public static void updateRoutesParams(Route update, Route updated) {
        update.setStartPoint(updated.getStartPoint());
        update.setDestinationPoint(updated.getDestinationPoint());
    }

    public static void updateDeliveryParams(Delivery update, Delivery updated) {
        update.setSize(updated.getSize());
        update.setTotalPrice(updated.getTotalPrice());
        update.setDeliveryStatus(updated.getDeliveryStatus());
        update.setRoute(updated.getRoute());
        update.setUser(updated.getUser());
    }
}
