package org.luggage_delivery.web.command.diff_command.manager_commands;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 15:22
*/

import org.hibernate.Session;
import org.luggage_delivery.dao.dao_implementations.DeliveryDAOImpl;
import org.luggage_delivery.dao.dao_implementations.RouteDAOImpl;
import org.luggage_delivery.entity.Route;
import org.luggage_delivery.exceptions.DataBaseException;
import org.luggage_delivery.report.report_factory.DeliveryReportFactory;
import org.luggage_delivery.report.report_factory.diff_factories.DayReportFactory;
import org.luggage_delivery.report.report_factory.diff_factories.RouteReportFactory;
import org.luggage_delivery.service.RouteService;
import org.luggage_delivery.service.service_impls.RouteServiceImpl;
import org.luggage_delivery.session_factory_config.HibernateUtil;
import org.luggage_delivery.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static org.luggage_delivery.pdf_creation.CreatePDF.createPDF;

public class CreateReportsCommand extends Command {

    @Override
    public String executeCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RouteService routeService = new RouteServiceImpl(new RouteDAOImpl(session));
        System.out.println(req.getParameter("report-date"));
        System.out.println(req.getParameter("routeId"));
        DeliveryReportFactory factory;

        try {

            if (req.getParameter("report-date") != null) {
                factory = new DayReportFactory(new DeliveryDAOImpl(session));
//                System.out.println("DAY REPORT: " +
                createPDF(factory.createDeliveryReport().createReport(Date.valueOf(req.getParameter("report-date"))),
                        req.getParameter("report-date") + " date. All orders that should be on this date are:");
            } else if (req.getParameter("routeId") != null) {
                factory = new RouteReportFactory(new DeliveryDAOImpl(session));
                Route route = routeService.getById(Integer.parseInt(req.getParameter("routeId")));
                //                System.out.println("ROUTE REPORT: " +
                createPDF(factory.createDeliveryReport().createReport(routeService.getById(Integer.parseInt(req.getParameter("routeId")))),
                        route.getStartPoint() + " -> " + route.getDestinationPoint() + " route. All orders made on this route are:");
            }

        } catch (DataBaseException ex) {
            ex.printStackTrace();
        }

//        req.setAttribute("downloadPdf", true);
        session.close();

        return "Luggage-delivery?cmd=report-view&downloadPdf=true";
    }
}