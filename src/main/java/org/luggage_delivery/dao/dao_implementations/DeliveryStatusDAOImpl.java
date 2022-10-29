package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 21:01
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_interfaces.DeliveryStatusDAO;
import org.luggage_delivery.entity.DeliveryStatus;
import org.luggage_delivery.util.UpdateUtil;

import java.util.List;

public class DeliveryStatusDAOImpl implements DeliveryStatusDAO {

    private final Session session;

    public DeliveryStatusDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewDeliveryStatus(DeliveryStatus status) {
        session.save(status);
    }

    @Override
    public List<DeliveryStatus> getAllDeliveryStatuses() {
        return session.createQuery("SELECT ds FROM DeliveryStatus ds", DeliveryStatus.class).list();
    }

    @Override
    public DeliveryStatus getById(int id) {
        return session.get(DeliveryStatus.class, id);
    }

    @Override
    public DeliveryStatus getByStatusName(String statusName) {
        return session.createQuery("SELECT ds FROM DeliveryStatus ds WHERE ds.statusName = :statusName",
                                              DeliveryStatus.class)
                .setParameter("statusName", statusName)
                .uniqueResult();
    }

    @Override
    public void updateDeliveryStatus(int id, DeliveryStatus status) {
        DeliveryStatus status1 = getById(id);
        UpdateUtil.updateDeliveryStatusParams(status1, status);
        session.update(status1);
    }

    @Override
    public void deleteDeliveryStatus(DeliveryStatus status) {
        session.createQuery("DELETE FROM DeliveryStatus ds WHERE ds.statusName = :statusName")
                .setParameter("statusName", status.getStatusName())
                .executeUpdate();
    }
}
