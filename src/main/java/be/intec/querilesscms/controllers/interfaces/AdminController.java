package be.intec.querilesscms.controllers.interfaces;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface AdminController {

    String showUsers(Model model);

    String deleteUser(@PathVariable Long id);

}
