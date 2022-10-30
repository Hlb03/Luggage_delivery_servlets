package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 24.10.2022
  Cur_time: 18:11
*/

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class PaginationUtil {

    public static int[] getDefaultPaginationData(HttpServletRequest req, int totalDataAmount) {
        int page;

        ResourceBundle rb = ResourceBundle.getBundle("pagination");
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
}
