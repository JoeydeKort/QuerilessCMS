package be.intec.querilesscms.controllers.interfaces;

import be.intec.querilesscms.models.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

public interface AdminController {

    String showUsers(Model model);

    String deleteUser(@PathVariable Long id);

    String showAddUserForm(Model model);

    String addNewUser(@Valid User user, BindingResult bindingResult, Model model);

    String showUpdateUserForm(Model model);

    String updateUser(@PathVariable(name = "id") Long id, Model model);

}
