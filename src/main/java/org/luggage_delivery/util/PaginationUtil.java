package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 24.10.2022
  Cur_time: 18:11
*/

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class PaginationUtil {

    private final static ResourceBundle rb = ResourceBundle.getBundle("pagination");

    public static int[] getDefaultRoutePaginationData(HttpServletRequest req, int totalDataAmount) {
        int page;

        int amountOfRoutesOnPage =  Integer.parseInt(rb.getString("main-page.routes"));
        System.out.println("INFO ABOUT AMOUNT OF ROUTE IN MAIN PAGE = " + amountOfRoutesOnPage);

        if (req.getParameter("page") == null)
            page = 1;
        else page = Integer.parseInt(req.getParameter("page"));
        System.out.println("CURRENT PAGE IS - " + page);

        int totalPages = totalDataAmount/amountOfRoutesOnPage;
        if (totalDataAmount % amountOfRoutesOnPage != 0)
            totalPages++;

        return new int[]{page, amountOfRoutesOnPage, totalPages};
    }

    public static int[] getDefaultUserOrderPaginationData(HttpServletRequest req, int totalAmount) {
        int page;

        int amountOfOrderPerPage = Integer.parseInt(rb.getString("user-room.order"));

        if (req.getParameter("page") == null)
            page = 1;
        else page = Integer.parseInt(req.getParameter("page"));

        int totalPages = totalAmount/amountOfOrderPerPage;
        if (totalAmount % amountOfOrderPerPage != 0)
            totalPages++;

        return new int[]{page, amountOfOrderPerPage, totalPages};
    }


}
