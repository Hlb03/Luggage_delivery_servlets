package org.luggage_delivery.service;

import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;

import java.sql.Date;
import java.util.List;

public interface DeliveryService {
    void addDelivery(Delivery delivery) throws DataBaseException;
    List<Delivery> getAll() throws DataBaseException;
    List<Delivery> getDeliveryByStatus(DeliveryStatus deliveryStatus) throws DataBaseException;
    List<Delivery> getDeliveriesByDate(Date date) throws DataBaseException;
    List<Delivery> getDeliveriesByRoute(Route route) throws DataBaseException;
    Delivery getDeliveryById(int id) throws DataBaseException;
    void updateDelivery(int id, Delivery delivery) throws DataBaseException;
    void deleteDelivery(Delivery delivery) throws DataBaseException;
}
