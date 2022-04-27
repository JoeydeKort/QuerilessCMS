package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.DatabaseController;
import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.DatabaseServiceImpl;
import be.intec.querilesscms.utils.BeerPDFExporter;
import be.intec.querilesscms.utils.BrewerPDFExporter;
import be.intec.querilesscms.utils.CategoryPDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DatabaseControllerImpl implements DatabaseController  {

    private final DatabaseServiceImpl databaseServiceImpl;

    public DatabaseControllerImpl(DatabaseServiceImpl databaseServiceImpl) {
        this.databaseServiceImpl = databaseServiceImpl;
    }

    @Override
    @GetMapping("/dbmenu")
    public String showDbmenu() {

        return "dbmenu/dbmenu";

    }

    @Override
    @GetMapping("/dbmenu/export-beer/pdf")
    public void exportBeerToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=beers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Beer> listOfBeers = databaseServiceImpl.findAllBeers();
        BeerPDFExporter exporter = new BeerPDFExporter(listOfBeers);
        exporter.export(response);

    }

    @Override
    @GetMapping("/dbmenu/export-brewer/pdf")
    public void exportBrewerToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=brewers_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Brewer> listOfBrewers = databaseServiceImpl.findAllBrewers();
        BrewerPDFExporter exporter = new BrewerPDFExporter(listOfBrewers);
        exporter.export(response);

    }

    @Override
    @GetMapping("/dbmenu/export-category/pdf")
    public void exportCategoryToPDF(HttpServletResponse response)  throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=category_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Category> listOfCategories = databaseServiceImpl.findAllCategory();
        CategoryPDFExporter exporter = new CategoryPDFExporter(listOfCategories);
        exporter.export(response);

    }

    @Override
    @GetMapping("/add-beer")
    public String showAddBeerForm(Model model) {

        Beer beer = new Beer();
        model.addAttribute("beer", beer);

        return "dbmenu/add-beer";
    }

    @Override
    @PostMapping("/add-beer")
    public String addNewBeer(@Valid Beer beer, BindingResult bindingResult, Model model) {

        if (databaseServiceImpl.findBeerByName(beer.getName()).isPresent()) {
            bindingResult.rejectValue("name", "error.beer", "There is already a beer registered with this name.");
        }

        if (!bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Beer has been successfully added.");
            model.addAttribute("beer", new Beer());

            databaseServiceImpl.saveBeer(beer);
        }

        return "dbmenu/add-beer";

    }

    @Override
    @GetMapping("/add-brewer")
    public String showAddBrewerForm(Model model) {

        Brewer brewer = new Brewer();
        model.addAttribute("brewer", brewer);

        return "dbmenu/add-brewer";
    }

    @Override
    @PostMapping("/add-brewer")
    public String addNewBrewer(Brewer brewer, BindingResult bindingResult, Model model) {

        if (databaseServiceImpl.findBeerByName(brewer.getName()).isPresent()) {
            bindingResult.rejectValue("name", "error.brewer", "There is already a brewer registered with this name.");
        }

        if (!bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Brewer has been successfully added.");
            model.addAttribute("brewer", new Brewer());

            databaseServiceImpl.saveBrewer(brewer);
        }

        return "dbmenu/add-brewer";
    }

    @Override
    @GetMapping("/add-category")
    public String showAddCategoryForm(Model model) {

        Category category = new Category();
        model.addAttribute("category", category);

        return "dbmenu/add-category";
    }

    @Override
    @PostMapping("/add-category")
    public String addNewCategory(Category category, BindingResult bindingResult, Model model) {

        if (databaseServiceImpl.findBeerByName(category.getTitle()).isPresent()) {
            bindingResult.rejectValue("title", "error.category", "There is already a category registered with this title.");
        }

        if (!bindingResult.hasErrors()) {
            model.addAttribute("successMessage", "Category has been successfully added.");
            model.addAttribute("category", new Category());

            databaseServiceImpl.saveCategory(category);
        }

        return "dbmenu/add-category";

    }

    @RequestMapping(path = {"/search"})
    public String home(Model model, String keyword) {

        if (keyword != null) {
            List<Beer> listBeer = databaseServiceImpl.getBeerByKeyword(keyword);
            model.addAttribute("listBeer", listBeer);

            List<Brewer> listBrewers = databaseServiceImpl.getBrewerByKeyword(keyword);
            model.addAttribute("listBrewers", listBrewers);

            List<Category> listCategories = databaseServiceImpl.getCategoryByKeyword(keyword);
            model.addAttribute("listCategories", listCategories);

        } else {
            return "dbmenu/dbmenu";
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("pageTitle", "Search result for: ' " + keyword + " '");

        return "dbmenu/search-results";
    }

}
