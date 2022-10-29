package org.luggage_delivery.service.service_impls;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 11:18
*/

import org.luggage_delivery.dao.dao_interfaces.RouteDAO;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private final RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    @Override
    public void addRoute(Route route) throws DataBaseException {
        try {
            routeDAO.addNewRoute(route);
        } catch (Exception e) {
            throw new DataBaseException("Failed to add new route", e);
        }
    }

    @Override
    public List<Route> getAllRoutes(int page, String rowToOrder, String typeOfOrder) throws DataBaseException {
        try {
            return routeDAO.getAllRoutes(page, rowToOrder, typeOfOrder);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get routes", e);
        }
    }

    @Override
    public long getRoutesAmount() throws DataBaseException {
        try {
            return routeDAO.getAmountOfRoutes();
        } catch (Exception e) {
            throw new DataBaseException("Failed to get routes amount", e);
        }
    }

    @Override
    public Route getById(int id) throws DataBaseException {
        try {
            return routeDAO.getById(id);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get route by id", e);
        }
    }

    @Override
    public Route getByRoute(String start, String end) throws DataBaseException {
        try {
            return routeDAO.getByRoute(start, end);
        } catch (Exception e) {
            throw new DataBaseException("Failed to get data by route", e);
        }
    }

    @Override
    public void updateRoute(int id, Route route) throws DataBaseException {
        try {
            routeDAO.updateRoute(id, route);
        } catch (Exception e) {
            throw new DataBaseException("Failed to update route", e);
        }
    }

    @Override
    public void deleteRoute(Route route) throws DataBaseException {
        try {
            routeDAO.deleteRoute(route);
        } catch (Exception e) {
            throw new DataBaseException("Failed to delete route");
        }
    }
}
