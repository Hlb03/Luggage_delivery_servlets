package org.luggage_delivery.service;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 15:30
*/

import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.exceptions.DataBaseException;

import java.util.List;

public interface TariffService {
    void addTariff(Tariff tariff) throws DataBaseException;
    List<Tariff> getTariffs(String rowToOrder, String typeOfOrder) throws DataBaseException;
    Tariff getById(int id) throws DataBaseException;
    Tariff getByType(String tariffType) throws DataBaseException;
    void updateTariff(int tariffId, Tariff tariff) throws DataBaseException;
    void deleteTariff(Tariff tariff) throws DataBaseException;
}
