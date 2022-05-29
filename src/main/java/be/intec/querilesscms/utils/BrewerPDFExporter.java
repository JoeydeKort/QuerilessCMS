package be.intec.querilesscms.utils;

import be.intec.querilesscms.models.Brewer;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BrewerPDFExporter {

    private List<Brewer> listBrewers;

    public BrewerPDFExporter(List<Brewer> listBrewers) {
        this.listBrewers = listBrewers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(6);

        cell.setPhrase(new Phrase("Brewer ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Address"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("City"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Zip"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Turnover"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Brewer brewer: listBrewers) {
            table.addCell(String.valueOf(brewer.getId()));
            table.addCell(brewer.getName());
            table.addCell(brewer.getAddress());
            table.addCell(brewer.getCity());
            table.addCell(brewer.getZip());
            table.addCell(brewer.getTurnover().toString());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of Brewers");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.0f, 3.0f, 3.0f, 1.5f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
