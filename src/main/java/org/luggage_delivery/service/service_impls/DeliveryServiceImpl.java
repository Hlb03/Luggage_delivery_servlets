package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 10:57
*/

import org.luggage_delivery.dao.dao_interfaces.DeliveryDAO;
import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.entity.User;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryService;

import java.sql.Date;
import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDAO deliveryDAO;

    public DeliveryServiceImpl(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public void addDelivery(Delivery delivery) throws DataBaseException {
        try {
            deliveryDAO.addNewDelivery(delivery);
        } catch (Exception e) {
            throw new DataBaseException("Failed to create new delivery", e);
        }
    }

    @Override
    public long getUserDeliveriesAmount(User user) throws DataBaseException {
        try {
            return deliveryDAO.getUserDeliveriesAmount(user);
        } catch (Exception e) {
            throw new DataBaseException();
        }
    }

    @Override
    public List<Delivery> getAll() throws DataBaseException {
        try {
            return deliveryDAO.getAll();
        } catch (Exception e) {
            throw new DataBaseException("Failed to get all deliveries", e);
        }
    }

    @Override
    public List<Delivery> getAllUserDeliveries(User user, int page, int dataAmount) throws DataBaseException {
        try {
             return deliveryDAO.getUserDeliveries(user, page, dataAmount);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get user with id " + user.getId() + " deliveries", e);
        }
    }

    @Override
    public List<Delivery> getDeliveryByStatus(DeliveryStatus deliveryStatus) throws DataBaseException {
        try {
            return deliveryDAO.getDeliveryByStatus(deliveryStatus);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get deliveries by status", e);
        }
    }

    @Override
    public List<Delivery> getDeliveriesByDate(Date date) throws DataBaseException {
        try {
            return deliveryDAO.getByDeliveryDate(date);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get all deliveries on date", e);
        }
    }

    @Override
    public List<Delivery> getDeliveriesByRoute(Route route) throws DataBaseException {
        try {
            return deliveryDAO.getByRoute(route);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get deliveries on route", e);
        }
    }

    @Override
    public Delivery getDeliveryById(int id) throws DataBaseException {
        try {
            return deliveryDAO.getById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get delivery by id", e);
        }
    }

    @Override
    public void updateDelivery(int id, Delivery delivery) throws DataBaseException {
        try {
            deliveryDAO.updateDelivery(id, delivery);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update delivery", e);
        }
    }

    @Override
    public void deleteDelivery(Delivery delivery) throws DataBaseException {
        try {
            deliveryDAO.deleteDelivery(delivery);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete delivery");
        }
    }
}
