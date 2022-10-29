package org.luggage_delivery.report.report_impls;
/*
  User: admin
  Cur_date: 22.10.2022
  Cur_time: 21:35
*/

import org.luggage_delivery.dao.dao_interfaces.DeliveryDAO;
import org.luggage_delivery.report.DeliveryReport;

import java.sql.Date;

public class DayReport implements DeliveryReport {

    private final DeliveryDAO deliveryDAO;

    public DayReport(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    @Override
    public void createReport(Object reportObject) {
        System.out.println(deliveryDAO.getByDeliveryDate((Date) reportObject));
    }
}
