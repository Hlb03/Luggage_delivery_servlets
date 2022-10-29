package org.luggage_delivery.report.report_impls;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 21:36
*/

import org.luggage_delivery.dao.dao_interfaces.DeliveryDAO;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.report.DeliveryReport;

public class RouteReport implements DeliveryReport {

    private final DeliveryDAO deliveryDAO;

    public RouteReport(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public void createReport(Object reportObject) {
        System.out.println(deliveryDAO.getByRoute((Route) reportObject));
    }
}
