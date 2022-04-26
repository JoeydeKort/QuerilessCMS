package be.intec.querilesscms.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import be.intec.querilesscms.models.Beer;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class BeerPDFExporter {

    private List<Beer> listBeers;

    public BeerPDFExporter(List<Beer> listBeers) {
        this.listBeers = listBeers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Beer ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Stock"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Alcohol"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Beer beer : listBeers) {
            table.addCell(String.valueOf(beer.getId()));
            table.addCell(beer.getName());
            table.addCell(beer.getPrice().toString());
            table.addCell(beer.getStock().toString());
            table.addCell(beer.getAlcohol().toString());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of Beers");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
