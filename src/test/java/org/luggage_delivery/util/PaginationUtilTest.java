package org.luggage_delivery.util;
/*
  User: admin
  Cur_date: 05.12.2022
  Cur_time: 14:33
*/

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaginationUtilTest {

    private final HttpServletRequest req = mock(HttpServletRequest.class);

    private final ResourceBundle rb = ResourceBundle.getBundle("pagination");


    @Test
    void getDefaultRoutePaginationDataWithNullPageTest() {
        int amountOfRoutesOnPage = Integer.parseInt(rb.getString("main-page.routes"));
        when(req.getParameter("page")).thenReturn(null);

        int totalPages = 20 / amountOfRoutesOnPage;

        int[] expected = new int[]{1, amountOfRoutesOnPage, totalPages};
        int[] inFact = PaginationUtil.getDefaultRoutePaginationData(req, 20);
        assertAll("expected",
                () -> assertEquals(expected[0], inFact[0]),
                () -> assertEquals(expected[1], inFact[1]),
                () -> assertEquals(expected[2], inFact[2])
        );

        verify(req, times(1)).getParameter("page");
    }

    @Test
    void getDefaultRoutePaginationDataWithValuePageTest() {
        int amountOfRoutesOnPage = Integer.parseInt(rb.getString("main-page.routes"));
        when(req.getParameter("page")).thenReturn("3");

        int totalPages = 30 / amountOfRoutesOnPage + 1;

        int[] expected = new int[]{3, amountOfRoutesOnPage, totalPages};
        int[] inFact = PaginationUtil.getDefaultRoutePaginationData(req, 30);
        assertAll("expected",
                () -> assertEquals(expected[0], inFact[0]),
                () -> assertEquals(expected[1], inFact[1]),
                () -> assertEquals(expected[2], inFact[2])
        );

        verify(req, times(2)).getParameter("page");
    }

    @Test
    void getDefaultUserOrderPaginationWithNullPageTest() {
        int amountOfOrderPerPage = Integer.parseInt(rb.getString("user-room.order"));
        when(req.getParameter("page")).thenReturn(null);

        int totalPages = 12 / amountOfOrderPerPage;

        int[] expected = new int[]{1, amountOfOrderPerPage, totalPages};
        int[] inFact = PaginationUtil.getDefaultUserOrderPaginationData(req, 12);
        assertAll("expected",
                () -> assertEquals(expected[0], inFact[0]),
                () -> assertEquals(expected[1], inFact[1]),
                () -> assertEquals(expected[2], inFact[2])
        );

        verify(req, times(1)).getParameter("page");
    }

    @Test
    void getDefaultUserOrderPaginationWithValuePageTest() {
        int amountOfOrderPerPage = Integer.parseInt(rb.getString("user-room.order"));
        when(req.getParameter("page")).thenReturn("2");

        int totalPages = 23 / amountOfOrderPerPage + 1;

        int[] expected = new int[]{2, amountOfOrderPerPage, totalPages};
        int[] inFact = PaginationUtil.getDefaultUserOrderPaginationData(req, 23);
        assertAll("expected",
                () -> assertEquals(expected[0], inFact[0]),
                () -> assertEquals(expected[1], inFact[1]),
                () -> assertEquals(expected[2], inFact[2])
        );

        verify(req, times(2)).getParameter("page");
    }
}
