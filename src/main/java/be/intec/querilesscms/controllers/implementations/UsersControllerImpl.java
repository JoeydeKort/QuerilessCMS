package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.UsersController;
import be.intec.querilesscms.models.Users;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UsersControllerImpl implements UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Long id) {



        Users user = usersService.findUserById(1L).get();
        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
