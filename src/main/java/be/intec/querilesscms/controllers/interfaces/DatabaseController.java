package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.Beer;
import be.intec.querilesscms.models.Brewer;
import be.intec.querilesscms.models.Category;
import com.lowagie.text.DocumentException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

    String search(Model model, String keyword);

    String deleteBeerRecord(@PathVariable Long id);
    String deleteBrewerRecord(@PathVariable Long id);
    String deleteCategoryRecord(@PathVariable Long id);


    String showUpdateBeerForm(@PathVariable(name = "id") Long id, Model model);
    String updateBeer(@ModelAttribute("beer") Beer beer);

    String showUpdateBrewerForm(@PathVariable(name = "id") Long id, Model model);
    String updateBrewer(@ModelAttribute("brewer") Brewer brewer);

    String showUpdateCategoryForm(@PathVariable(name = "id") Long id, Model model);
    String updateCategory(@ModelAttribute("category") Category category);

}
