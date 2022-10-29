package org.luggage_delivery.service;

import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.exceptions.DataBaseException;

import java.util.List;

public interface DeliveryStatusService {
    void addNewStatus(DeliveryStatus deliveryStatus) throws DataBaseException;
    List<DeliveryStatus> getAllStatuses() throws DataBaseException;
    DeliveryStatus getStatusById(int id) throws DataBaseException;
    DeliveryStatus getStatusByName(String statusName) throws DataBaseException;
    void updateStatus(int id, DeliveryStatus status) throws DataBaseException;
    void deleteStatus(DeliveryStatus deliveryStatus) throws DataBaseException;
}
