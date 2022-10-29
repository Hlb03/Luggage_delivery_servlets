package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 24.10.2022
  Cur_time: 18:11
*/

import javax.servlet.http.HttpServletRequest;

public class PaginationUtil {

    public static int[] getDefaultPaginationData(HttpServletRequest req) {
        int page = 0;

        if (req.getParameter("page") == null)
            page = 1;

        return new int[]{page, 4};
    }
}
