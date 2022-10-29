package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 23.10.2022
  Cur_time: 15:35
*/

import org.luggage_delivery.dao.dao_interfaces.TariffDAO;
import org.luggage_delivery.entity.Tariff;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.TariffService;

import java.util.List;

public class TariffServiceImpl implements TariffService {

    public final TariffDAO tariffDAO;

    public TariffServiceImpl(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    public void addTariff(Tariff tariff) throws DataBaseException {
        try {
            tariffDAO.addNewTariff(tariff);
        } catch (Exception e) {
            throw new DataBaseException("Failed to add new tariff", e);
        }
    }

    @Override
    public List<Tariff> getTariffs(String rowToOrder, String typeOfOrder) throws DataBaseException {
        try {
            return tariffDAO.getAllTariffs(rowToOrder, typeOfOrder);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get all tariffs", e);
        }
    }

    @Override
    public Tariff getById(int id) throws DataBaseException {
        try {
            return tariffDAO.getTariffById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get tariff by id", e);
        }
    }

    @Override
    public Tariff getByType(String tariffType) throws DataBaseException {
        try {
            return tariffDAO.getTariffByType(tariffType);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get tariff by type", e);
        }
    }

    @Override
    public void updateTariff(int tariffId, Tariff tariff) throws DataBaseException {
        try {
            tariffDAO.updateTariff(tariffId, tariff);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update tariff", e);
        }
    }

    @Override
    public void deleteTariff(Tariff tariff) throws DataBaseException {
        try {
            tariffDAO.deleteTariff(tariff);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete tariff", e);
        }
    }
}
