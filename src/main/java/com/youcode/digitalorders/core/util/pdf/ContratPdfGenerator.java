package com.youcode.digitalorders.core.util.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.youcode.digitalorders.core.dao.model.entity.Contrat;
import com.youcode.digitalorders.core.dao.model.entity.DemandDetail;
import com.youcode.digitalorders.core.dao.model.entity.EquipmentPiece;
import com.youcode.digitalorders.core.dao.model.entity.User;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ContratPdfGenerator {

    public void generate(List<Contrat> contratList, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20);
        titleFont.setSize(20);

        Paragraph title = new Paragraph("List of Contracts", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{2, 3, 3, 3, 3});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.BLUE);
        cell.setPadding(5);

        Font headerFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        headerFont.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("Contract ID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User Name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment UUID", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Equipment Name", headerFont));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", headerFont));
        table.addCell(cell);

        for (Contrat contrat : contratList) {
            User user = contrat.getDevis().getDemand().getUser();
            List<DemandDetail> demandDetails = contrat.getDevis().getDemand().getDemandDetails();

            for (DemandDetail demandDetail : demandDetails) {
                EquipmentPiece equipmentPiece = demandDetail.getEquipmentPiece();

                table.addCell(String.valueOf(contrat.getId()));
                table.addCell(user.getName());
                table.addCell(equipmentPiece.getUUID().toString());
                table.addCell(equipmentPiece.getEquipment().getName());
                table.addCell(String.valueOf(equipmentPiece.getPrice()));
            }
        }

        document.add(table);
        document.close();
    }
}
