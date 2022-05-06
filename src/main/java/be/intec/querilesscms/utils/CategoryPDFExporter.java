package be.intec.querilesscms.utils;

import be.intec.querilesscms.models.Category;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CategoryPDFExporter {

    private List<Category> listCategories;

    public CategoryPDFExporter(List<Category> listCategories) {
        this.listCategories = listCategories;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(3);

        cell.setPhrase(new Phrase("Category ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Title"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Slug"));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Category category: listCategories) {
            table.addCell(String.valueOf(category.getId()));
            table.addCell(category.getTitle());
            table.addCell(category.getSlug());

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of Brewers");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 4.0f, 4.0f,});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }


}
