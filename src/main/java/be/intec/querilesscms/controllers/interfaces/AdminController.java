package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.User;
import com.lowagie.text.DocumentException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

public interface AdminController {

    String showUsers(Model model);

    String deleteUser(@PathVariable Long id);

    String showAddUserForm(Model model);

    String addNewUser(@Valid User user, BindingResult bindingResult, Model model);

    String showUpdateUserForm(@PathVariable(name = "id") Long id, Model model);

    void exportUserToPDF(HttpServletResponse response)  throws DocumentException, IOException;

    public void exportLogDataToPDF(HttpServletResponse response) throws DocumentException, IOException;

    String updateUser(@ModelAttribute("user") User user);
}
