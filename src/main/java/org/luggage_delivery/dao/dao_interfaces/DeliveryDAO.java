package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.Route;

import java.sql.Date;
import java.util.List;

public interface DeliveryDAO {
    void addNewDelivery(Delivery delivery);
    List<Delivery> getAll();
    List<Delivery> getDeliveryByStatus(DeliveryStatus status);
    List<Delivery> getByDeliveryDate(Date deliveryDate);
    List<Delivery> getByRoute(Route route);
    Delivery getById(int id);
    void updateDelivery(int id, Delivery delivery);
    void deleteDelivery(Delivery delivery);
}