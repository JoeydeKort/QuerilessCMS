package be.intec.querilesscms.utils;

import be.intec.querilesscms.models.User;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserPDFExporter {

    private List<User> listUsers;

    public UserPDFExporter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(8);

        cell.setPhrase(new Phrase("User ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Username"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Firstname"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Lastname"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Address"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("City"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Zip"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (User user : listUsers) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getUsername());
            table.addCell(user.getEmail());
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());
            table.addCell(user.getAddress());
            table.addCell(user.getCity());
            table.addCell(user.getZip());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Paragraph p = new Paragraph("List of Users");
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 2.5f, 2.0f, 2.5f, 2.5f, 2.5f, 2.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }

}
