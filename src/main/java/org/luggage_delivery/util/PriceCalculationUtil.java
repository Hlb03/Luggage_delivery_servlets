package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 16:03
*/

import org.luggage_delivery.entity.Delivery;

import java.math.BigDecimal;
import java.util.ResourceBundle;

public class PriceCalculationUtil {

    public static BigDecimal calculateGeneralPrice(Delivery delivery, String externalOption) {
        BigDecimal totalPrice = new BigDecimal("0");

        ResourceBundle rb = ResourceBundle.getBundle("all-tariffs");
//        System.out.println(rb.getString("size") + " * " + delivery.getSize() + " = " +
//                new BigDecimal(rb.getString("size")).multiply(delivery.getSize()));

//        System.out.println(rb.getString("distance") + " * " + delivery.getRoute().getDistance() + " = " +
//                new BigDecimal(rb.getString("distance")).multiply(new BigDecimal(delivery.getRoute().getDistance().toString())));

//        System.out.println(rb.getString("weight") + " * " + delivery.getWeight() + " = " +
//                new BigDecimal(rb.getString("weight")).multiply(delivery.getWeight()));

//        if (externalOption.equals("Fragile"))
//            System.out.println(rb.getString("fragile") + " * " + externalOption + " = " +
//                    new BigDecimal(rb.getString("fragile")));

        totalPrice = totalPrice.add(new BigDecimal(rb.getString("size")).multiply(delivery.getSize()));
        totalPrice = totalPrice.add(new BigDecimal(rb.getString("distance")).multiply(new BigDecimal(delivery.getRoute().getDistance().toString())));
        totalPrice = totalPrice.add(new BigDecimal(rb.getString("weight")).multiply(delivery.getWeight()));

        if (externalOption.equals("Fragile"))
            totalPrice = totalPrice.multiply(new BigDecimal(rb.getString("fragile")));

        System.out.println("TOTAL PRICE = " + totalPrice);

        if (totalPrice.toString().split("\\.")[1].length() > 2) {
            totalPrice = totalPrice.add(new BigDecimal("0.01"));
            System.out.println("PRICE AFTER ADDING 0.01 " + totalPrice);
            totalPrice = new BigDecimal(totalPrice.toString().substring(0,
                   totalPrice.toString().length() - cutExtraSymbolsInPrice(totalPrice.toString())));
        }

        System.out.println("PRICE AFTER CUTTING " + totalPrice);
        return totalPrice;
    }

    private static int cutExtraSymbolsInPrice(String price) {
        int amountOfSymbols = 0;
        int amountOfSymbolAfterDotInPrice = price.split("\\.")[1].length();

        for (; amountOfSymbols < amountOfSymbolAfterDotInPrice - 2; amountOfSymbols++) {}

        System.out.println("Amount of symbols to cut off " + amountOfSymbols);
        return amountOfSymbols;
    }
}