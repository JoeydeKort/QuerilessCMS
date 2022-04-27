package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;
import com.lowagie.text.DocumentException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

public interface DatabaseController {

    String showDbmenu();
    void exportBeerToPDF(HttpServletResponse response) throws DocumentException, IOException;
    void exportBrewerToPDF(HttpServletResponse response)  throws DocumentException, IOException;
    void exportCategoryToPDF(HttpServletResponse response)  throws DocumentException, IOException;

    String showAddBeerForm(Model model);
    String addNewBeer(@Valid Beer beer, BindingResult bindingResult, Model model);

    String showAddBrewerForm(Model model);
    String addNewBrewer(@Valid Brewer brewer, BindingResult bindingResult, Model model);

    String showAddCategoryForm(Model model);
    String addNewCategory(@Valid Category category, BindingResult bindingResult, Model model);
}
