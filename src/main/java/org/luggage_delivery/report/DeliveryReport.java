package org.luggage_delivery.report;

import org.luggage_delivery.entity.Delivery;

import java.util.List;

public interface DeliveryReport {

    List<Delivery> createReport(Object reportObject);
}
