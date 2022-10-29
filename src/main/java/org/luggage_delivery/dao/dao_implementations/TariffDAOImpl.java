package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 17:59
*/

import org.hibernate.Session;
import org.hibernate.annotations.OrderBy;
import org.luggage_delivery.dao.dao_interfaces.TariffDAO;
import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.util.UpdateUtil;

import java.util.List;

public class TariffDAOImpl implements TariffDAO {

    private final Session session;

    public TariffDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewTariff(Tariff tariff) {
        session.save(tariff);
    }

    @Override
    public List<Tariff> getAllTariffs(String rowToOrder, String typeOfOrder) {
        return session.createQuery("SELECT t FROM Tariff t ORDER BY " + rowToOrder + " " + typeOfOrder,
                Tariff.class).list();
    }

    @Override
    public Tariff getTariffById(int id) {
        return session.get(Tariff.class, id);
    }

    @Override
    public Tariff getTariffByType(String tariffType) {
        return session.createQuery("SELECT t FROM Tariff t WHERE t.type = :tariffType", Tariff.class)
                .setParameter("tariffType", tariffType).uniqueResult();
    }

    @Override
    public void updateTariff(int id, Tariff tariff) {
        Tariff tariff1 = getTariffById(id);
        UpdateUtil.updateTariffParams(tariff1, tariff);
        session.update(tariff1);
    }

    @Override
    public void deleteTariff(Tariff tariff) {
        session.createQuery("DELETE FROM Tariff t WHERE t.type = :type AND t.price = :price")
                .setParameter("type", tariff.getType()).setParameter("price", tariff.getPrice())
                .executeUpdate();
    }
}
