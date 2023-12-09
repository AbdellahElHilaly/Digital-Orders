package com.youcode.digitalorders.core.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.Demand;
import com.youcode.digitalorders.core.dao.model.entity.Equipment;
import com.youcode.digitalorders.core.dao.model.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
public class ContratPdfGenerator {

    private PdfWriter writer;

    public void generate(Contrat contrat, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Increase title font size
        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 60);
        Paragraph title = new Paragraph("Contract", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Add space after the header
        document.add(new Paragraph(" "));

        // Add rules of renting equipment
        addRules(document);

        // Add space after the rules
        document.add(new Paragraph(" "));

        // Add contract details in a vertical table
        PdfPTable detailsTable = new PdfPTable(2);
        detailsTable.setWidthPercentage(100);
        detailsTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        User user = contrat.getDevis().getDemand().getUser();
        Demand demandDetail = contrat.getDevis().getDemand();
        Equipment equipment = demandDetail.getEquipment();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Add headers and values vertically
        detailsTable.addCell(createHeaderCell("User Name"));
        detailsTable.addCell(createValueCell(user.getName()));

        detailsTable.addCell(createHeaderCell("User Email"));
        detailsTable.addCell(createValueCell(user.getEmail()));

        detailsTable.addCell(createHeaderCell("Equipment Name"));
        detailsTable.addCell(createValueCell(equipment.getName()));

        detailsTable.addCell(createHeaderCell("Reservation S. date"));
        detailsTable.addCell(createValueCell(dateFormat.format(demandDetail.getStartDate())));

        detailsTable.addCell(createHeaderCell("Reservation End date"));
        detailsTable.addCell(createValueCell(dateFormat.format(demandDetail.getEndDate())));

        detailsTable.addCell(createHeaderCell("Price en DH"));
        detailsTable.addCell(createValueCell(String.valueOf(equipment.getPrice())));

        document.add(detailsTable);

        // Add space after the table
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        // Add signature section
        addSignature(document);

        document.close();
    }

    private PdfPCell createHeaderCell(String header) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.WHITE);
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Font headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD);
        headerFont.setSize(12);
        headerFont.setColor(CMYKColor.BLACK);

        cell.addElement(new Phrase(header, headerFont));

        return cell;
    }

    private PdfPCell createValueCell(String value) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);

        Font valueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        valueFont.setSize(12);
        valueFont.setColor(CMYKColor.BLACK);

        cell.addElement(new Phrase(value, valueFont));

        return cell;
    }

    private void addRules(Document document) throws DocumentException {
        Paragraph rulesParagraph = new Paragraph();
        rulesParagraph.setAlignment(Element.ALIGN_LEFT);

        Font ruleTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("Rules of Renting Equipment:\n", ruleTitle);
        rulesParagraph.add(title);

        Font ruleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        // Add a space after the rules
        document.add(new Paragraph(" "));

        rulesParagraph.add("1. Equipment must be used for its intended purpose.\n");
        rulesParagraph.add("2. Any damages or malfunctions must be reported immediately.\n");
        rulesParagraph.add("3. The renter is responsible for equipment maintenance.\n");
        rulesParagraph.add("4. Late returns may incur additional charges.\n");

        document.add(rulesParagraph);
    }

    private void addSignature(Document document) throws DocumentException {
        PdfContentByte canvas = writer.getDirectContent();

        // Adjust these coordinates as needed
        float x = document.right() - 150;
        float y = document.bottom() + 50;

        Phrase phrase = new Phrase("Signature: ...............................", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));

        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase, x, y, 0);
    }
}
