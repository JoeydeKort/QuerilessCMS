package be.intec.querilesscms.controllers.implementations;


import be.intec.querilesscms.controllers.interfaces.AdminController;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class AdminControllerImpl implements AdminController {

    private final UsersServiceImpl usersServiceImpl;

    public AdminControllerImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("/admin")
    public String showUsers(Model model) {

        List<User> users = usersServiceImpl.findAllUsers();
        model.addAttribute("users", users);

        return "admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable Long id){

        usersServiceImpl.deleteById(id);

        return "redirect:/admin";

    }

}
