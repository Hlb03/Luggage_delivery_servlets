package org.luggage_delivery.web.filter;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 23:33
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ManagerPagesRejectFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(ManagerPagesRejectFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String command = req.getParameter("cmd");

        if (("manager-room".equals(command) || "changeStatus".equals(command)
                || "report-view".equals(command) || "make-report".equals(command))
                && !"MANAGER".equals(req.getSession().getAttribute("userRole"))){
            LOG.debug("User can't view manager pages...Redirect to authorization page...");
            resp.sendRedirect("Luggage-delivery?cmd=authorize");
        } else chain.doFilter(request, response);

    }
}
