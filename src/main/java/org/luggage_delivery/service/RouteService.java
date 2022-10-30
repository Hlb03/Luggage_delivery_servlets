package org.luggage_delivery.service;

import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;

import java.util.List;

public interface RouteService {
    void addRoute(Route route) throws DataBaseException;
    List<Route> getAllRoutes(int page, int dataAmount, String rowToOrder, String typeOfOrder) throws DataBaseException;
    long getRoutesAmount() throws DataBaseException;
    Route getById(int id) throws DataBaseException;
    Route getByRoute(String start, String end) throws DataBaseException;
    void updateRoute(int id, Route route) throws DataBaseException;
    void deleteRoute(Route route) throws DataBaseException;
}
