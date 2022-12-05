package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 16:03
*/

import org.luggage_delivery.entity.Delivery;
import org.luggage_delivery.web.command.diff_command.AddNewUserCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ResourceBundle;

public class PriceCalculationUtil {

    private final static Logger LOG = LoggerFactory.getLogger(PriceCalculationUtil.class);

    public static BigDecimal calculateGeneralPrice(Delivery delivery, String externalOption) {
        BigDecimal totalPrice = new BigDecimal("0");

        ResourceBundle rb = ResourceBundle.getBundle("all-tariffs");

        totalPrice = totalPrice.add(new BigDecimal(rb.getString("size")).multiply(delivery.getSize()));
        totalPrice = totalPrice.add(new BigDecimal(rb.getString("distance")).multiply(new BigDecimal(delivery.getRoute().getDistance().toString())));
        totalPrice = totalPrice.add(new BigDecimal(rb.getString("weight")).multiply(delivery.getWeight()));

        if (externalOption.equals("Fragile"))
            totalPrice = totalPrice.multiply(new BigDecimal(rb.getString("fragile")));

        LOG.debug("TOTAL PRICE = " + totalPrice);

        if (totalPrice.toString().split("\\.")[1].length() > 2) {
            totalPrice = totalPrice.add(new BigDecimal("0.01"));
            LOG.debug("PRICE AFTER ADDING 0.01 " + totalPrice);
            totalPrice = new BigDecimal(totalPrice.toString().substring(0,
                   totalPrice.toString().length() - cutExtraSymbolsInPrice(totalPrice.toString())));
        }

        LOG.debug("PRICE AFTER CUTTING " + totalPrice);
        return totalPrice;
    }

    private static int cutExtraSymbolsInPrice(String price) {
        int amountOfSymbols = 0;
        int amountOfSymbolAfterDotInPrice = price.split("\\.")[1].length();

        for (; amountOfSymbols < amountOfSymbolAfterDotInPrice - 2; amountOfSymbols++) {}

        LOG.debug("Amount of symbols to cut off " + amountOfSymbols);
        return amountOfSymbols;
    }
}
