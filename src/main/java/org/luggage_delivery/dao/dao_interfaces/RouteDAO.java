package org.luggage_delivery.dao.dao_interfaces;

import org.luggage_delivery.entity.Route;

import java.util.List;

public interface RouteDAO {
    void addNewRoute(Route route);
    List<Route> getAllRoutes(int page, String rowToOrder, String typeOfOrder);
    long getAmountOfRoutes();
    Route getById(int id);
    Route getByRoute(String start, String end);
    void updateRoute(int id, Route route);
    void deleteRoute(Route route);
}
