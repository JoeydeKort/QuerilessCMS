package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.DatabaseController;
import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;
import be.intec.querilesscms.services.Implementations.DatabaseServiceImpl;
import be.intec.querilesscms.utils.BeerPDFExporter;
import be.intec.querilesscms.utils.BrewerPDFExporter;
import be.intec.querilesscms.utils.CategoryPDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DatabaseControllerImpl implements DatabaseController  {

    private final DatabaseServiceImpl DatabaseServiceImpl;

    public DatabaseControllerImpl(DatabaseServiceImpl databaseServiceImpl) {
        DatabaseServiceImpl = databaseServiceImpl;
    }

    @GetMapping("/dbmenu")
    public String showDbmenu() {

        return "dbmenu/dbmenu";

    }

    @GetMapping("/dbmenu/export-beer/pdf")
    public void exportBeerToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=beers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Beer> listOfBeers = DatabaseServiceImpl.findAllBeers();
        BeerPDFExporter exporter = new BeerPDFExporter(listOfBeers);
        exporter.export(response);

    }

    @GetMapping("/dbmenu/export-brewer/pdf")
    public void exportBrewerToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=brewers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Brewer> listOfBrewers = DatabaseServiceImpl.findAllBrewers();
        BrewerPDFExporter exporter = new BrewerPDFExporter(listOfBrewers);
        exporter.export(response);

    }

    @GetMapping("/dbmenu/export-category/pdf")
    public void exportCategoryToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=category_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Category> listOfCategories = DatabaseServiceImpl.findAllCategory();
        CategoryPDFExporter exporter = new CategoryPDFExporter(listOfCategories);
        exporter.export(response);

    }

}
