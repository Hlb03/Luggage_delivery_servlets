package org.luggage_delivery.web.filter;
/*
  User: admin
  Cur_date: 30.10.2022
  Cur_time: 13:50
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class RestrictToViewProfileFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(RestrictToViewProfileFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String cmdName = req.getParameter("cmd");

        if (cmdName != null && cmdName.equals("user-room") && req.getSession().getAttribute("user") == null) {
            LOG.debug("You can't view profile till not authorized. Redirect to main menu...");
            resp.sendRedirect("Luggage-delivery");
        } else chain.doFilter(request, response);

    }
}
