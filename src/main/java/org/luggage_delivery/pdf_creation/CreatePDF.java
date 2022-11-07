package org.luggage_delivery.pdf_creation;
/*
  User: admin
  Cur_date: 07.11.2022
  Cur_time: 16:29
*/

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.util.file.File;
import org.luggage_delivery.entity.Delivery;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class CreatePDF {

    public static void createPDF(List<Delivery> deliveries, String objectOfReport) {
        deletePreviousReport();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document,
                    Files.newOutputStream(Paths.get("C:\\Users\\admin\\OneDrive\\Рабочий стол\\KPI_studying\\V semestr\\" +
                            "Java web-programming\\Lab_3\\Luggage-delivery\\src\\main\\resources\\pdfs\\reportOne.pdf")));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            createReportBody(deliveries, document, font, objectOfReport);

            document.close();
            copyPdf();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createReportBody(List<Delivery> deliveries, Document document, Font font, String objectOfReport)
                                                throws DocumentException {
        document.add(new Chunk("The statics was made " + LocalDate.now(), font));
        document.add(new Paragraph("The chosen subject of statistics is " + objectOfReport, font));

        for (Delivery d : deliveries){

            String report = "\n" + "The size of order is:" + d.getSize() +
                    ", its weight " + d.getWeight() +
                    ".\nThe order was made on " + d.getStartDate() +
                    " and should be on " + d.getDeliveryDate() +
                    ".\nDelivery address is: " + d.getDeliveryAddress() +
                    ".\n" + "Total price of order is: " + d.getTotalPrice() +
                    "\nOrder was made by user with login - " + d.getUser().getLogin() +
                    "\nOrder has route: " + d.getRoute().getStartPoint() +
                    " -> " + d.getRoute().getDestinationPoint() +
                    "\n\n";

           document.add(new Paragraph(report, font));
        }
    }

    private static void copyPdf() throws IOException {
        File originalPdf = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\KPI_studying\\V semestr" +
                    "\\Java web-programming\\Lab_3\\Luggage-delivery\\src\\main\\resources\\pdfs\\reportOne.pdf");

        Path copied = Paths.get("C:\\Users\\admin\\OneDrive\\Рабочий стол\\KPI_studying\\V semestr" +
                "\\Java web-programming\\Lab_3\\Luggage-delivery\\src\\main\\webapp\\pdf_reports\\reportStatistics.pdf");

        Path originalPath = originalPdf.toPath();
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
    }

    private static boolean deletePreviousReport() {
        File file = new File("C:\\Users\\admin\\OneDrive\\Рабочий стол\\KPI_studying\\V semestr" +
                "\\Java web-programming\\Lab_3\\Luggage-delivery\\src\\main\\webapp\\pdf_reports\\reportStatistics.pdf");

        return file.delete();
    }
}
