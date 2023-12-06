package com.youcode.digitalorders.core.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.youcode.digitalorders.core.dao.model.entity.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ContratPdfGenerator {

    public void generate(Contrat contrat, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20);
        titleFont.setSize(20);
        Paragraph title = new Paragraph("List of Contracts", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        // Add header with image
//        addHeader(document);

        // Add a space after the header
        document.add(new Paragraph(" "));

        // Add rules of renting equipment
        addRules(document);

        // Add a space after the rules
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{2, 3, 3, 3, 3});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
        cell.setPadding(5);

        Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        headerFont.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("User Name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Contract ID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment UUID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment Name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", headerFont));
        table.addCell(cell);

        // Add contract details

        User user = contrat.getDevis().getDemand().getUser();
        Demand demandDetail = contrat.getDevis().getDemand();
        Equipment equipmentPiece = demandDetail.getEquipment();

        table.addCell(String.valueOf(contrat.getId()));
        table.addCell(user.getName());
        table.addCell(equipmentPiece.getId().toString());
        table.addCell(equipmentPiece.getName());
        table.addCell(String.valueOf(equipmentPiece.getPrice()));


        // Add a space after the table
        document.add(new Paragraph(" "));

        document.add(table);

        // Add signature section
        addSignature(document);

        document.close();
    }

//    private void addHeader(Document document) throws DocumentException, IOException {
//        PdfPTable headerTable = new PdfPTable(1);
//        headerTable.setWidthPercentage(100f);
//
//        Image logo = Image.getInstance("src/main/resources/image/70221c42a8366ee389eba2f83d7c4fa3.jpg"); // Replace with the path to your logo
//        PdfPCell logoCell = new PdfPCell(logo, true);
//        logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        logoCell.setBorder(Rectangle.NO_BORDER);
//
//        headerTable.addCell(logoCell);
//
//        document.add(headerTable);
//    }

    private void addRules(Document document) throws DocumentException {
        Paragraph rulesParagraph = new Paragraph();
        rulesParagraph.setAlignment(Element.ALIGN_LEFT);

        Font ruleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

        rulesParagraph.add("Rules of Renting Equipment:\n");
        rulesParagraph.add("1. Equipment must be used for its intended purpose.\n");
        rulesParagraph.add("2. Any damages or malfunctions must be reported immediately.\n");
        rulesParagraph.add("3. The renter is responsible for equipment maintenance.\n");
        rulesParagraph.add("4. Late returns may incur additional charges.\n");

        document.add(rulesParagraph);
    }

    private void addSignature(Document document) throws DocumentException {
        Paragraph signatureParagraph = new Paragraph();
        signatureParagraph.setAlignment(Element.ALIGN_RIGHT);

        Font signatureFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

        signatureParagraph.add("Signature: ________________________\n");

        document.add(signatureParagraph);
    }
}
