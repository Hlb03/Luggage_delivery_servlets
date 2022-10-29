package org.luggage_delivery.dao.dao_implementations;
/*
  User: admin
  Cur_date: 15.10.2022
  Cur_time: 21:14
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_interfaces.RouteDAO;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.util.UpdateUtil;

import java.util.List;

public class RouteDAOImpl implements RouteDAO {

    private final Session session;

    public RouteDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public void addNewRoute(Route route) {
        session.save(route);
    }

    @Override
    public List<Route> getAllRoutes(int page, String rowToOrder, String typeOfOrder) {
        return session.createQuery("SELECT r FROM Route r ORDER BY " + rowToOrder + " " + typeOfOrder
                , Route.class).setMaxResults(4).list();

        // .setMaxResults(4)
//        return session.createQuery("SELECT r FROM Route r", Route.class).list();
    }

    @Override
    public long getAmountOfRoutes() {
        return (long) session.createQuery("SELECT COUNT(*) FROM Route r").uniqueResult();
    }

    @Override
    public Route getById(int id) {
        return session.get(Route.class, id);
    }

    @Override
    public Route getByRoute(String start, String end) {
        return session.createQuery("SELECT rt FROM Route rt WHERE rt.startPoint = :start AND rt.destinationPoint = :end",
                                              Route.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .uniqueResult();
    }

    @Override
    public void updateRoute(int id, Route route) {
        Route route1 = getById(id);
        UpdateUtil.updateRoutesParams(route1, route);
        session.update(route1);
    }

    @Override
    public void deleteRoute(Route route) {
        session.createQuery("DELETE FROM Route rt WHERE rt.startPoint = :start " +
                "AND rt.destinationPoint = :end")
                .setParameter("start", route.getStartPoint())
                .setParameter("end", route.getDestinationPoint())
                .executeUpdate();
    }
}
