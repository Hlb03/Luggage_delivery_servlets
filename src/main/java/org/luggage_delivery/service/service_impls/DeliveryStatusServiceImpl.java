package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 11:07
*/

import org.luggage_delivery.dao.dao_interfaces.DeliveryStatusDAO;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.DeliveryStatusService;

import java.util.List;

public class DeliveryStatusServiceImpl implements DeliveryStatusService {

    private final DeliveryStatusDAO deliveryStatusDAO;

    public DeliveryStatusServiceImpl(DeliveryStatusDAO deliveryStatusDAO) {
        this.deliveryStatusDAO = deliveryStatusDAO;
    }

    @Override
    public void addNewStatus(DeliveryStatus deliveryStatus) throws DataBaseException {
        try {
            deliveryStatusDAO.addNewDeliveryStatus(deliveryStatus);
        } catch (Exception e) {
            throw new DataBaseException("Failed to add new status", e);
        }
    }

    @Override
    public List<DeliveryStatus> getAllStatuses() throws DataBaseException {
        try {
            return deliveryStatusDAO.getAllDeliveryStatuses();
        } catch (Exception e) {
            throw new DataBaseException("Failed to get list of statuses", e);
        }
    }

    @Override
    public DeliveryStatus getStatusById(int id) throws DataBaseException {
        try {
            return deliveryStatusDAO.getById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get status by id", e);
        }
    }

    @Override
    public DeliveryStatus getStatusByName(String statusName) throws DataBaseException {
        try {
            return deliveryStatusDAO.getByStatusName(statusName);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get status by name", e);
        }
    }

    @Override
    public void updateStatus(int id, DeliveryStatus status) throws DataBaseException {
        try {
            deliveryStatusDAO.updateDeliveryStatus(id, status);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update status", e);
        }
    }

    @Override
    public void deleteStatus(DeliveryStatus deliveryStatus) throws DataBaseException {
        try {
            deliveryStatusDAO.deleteDeliveryStatus(deliveryStatus);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete status");
        }
    }
}
