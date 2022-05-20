package be.intec.querilesscms.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.util.List;


public class LoggerDataPDFExporter {

    private List<String> resultLoggerDataList;

    public LoggerDataPDFExporter(List<String> resultLoggerDataList) throws IOException {
        this.resultLoggerDataList = resultLoggerDataList;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(1);

        cell.setPhrase(new Phrase("Log Data"));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (String logData : resultLoggerDataList) {
            table.addCell(String.valueOf(logData));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of Log Data");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {16.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}


