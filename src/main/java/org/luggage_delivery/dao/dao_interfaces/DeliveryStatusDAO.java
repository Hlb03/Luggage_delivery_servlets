package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.DeliveryStatus;

import java.util.List;

public interface DeliveryStatusDAO {
    void addNewDeliveryStatus(DeliveryStatus status);
    List<DeliveryStatus> getAllDeliveryStatuses();
    DeliveryStatus getById(int id);
    DeliveryStatus getByStatusName(String statusName);
    void updateDeliveryStatus(int id, DeliveryStatus status);
    void deleteDeliveryStatus(DeliveryStatus status);
}
