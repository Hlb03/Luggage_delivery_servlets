package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.Tariff;

import java.util.List;

public interface TariffDAO {
    void addNewTariff(Tariff tariff);
    List<Tariff> getAllTariffs(String rowToOrder, String typeOfOrder);
    Tariff getTariffById(int id);
    Tariff getTariffByType(String tariffType);
    void updateTariff(int id, Tariff tariff);
    void deleteTariff(Tariff tariff);
}
