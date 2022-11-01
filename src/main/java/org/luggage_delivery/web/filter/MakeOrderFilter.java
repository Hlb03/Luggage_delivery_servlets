package org.luggage_delivery.web.filter;
/*
  User: admin
  Cur_date: 29.10.2022
  Cur_time: 17:31
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MakeOrderFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(MakeOrderFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String cmdParam = req.getParameter("cmd");
        System.out.println("CHECK WHETHER 'PRICE CALCULATE' WAS PRESSED' " + req.getParameter("priceCalculateButton"));

        if (cmdParam != null && cmdParam.equals("order-process") && req.getSession().getAttribute("user") == null
                    && req.getParameter("orderButton") != null){
            LOG.debug("Not authorized user trying to make order. Redirecting hin to authorization page...");
            resp.sendRedirect("Luggage-delivery?cmd=authorize");
        }else chain.doFilter(request, response);

    }
}
