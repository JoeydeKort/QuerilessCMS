package be.intec.querilesscms.controllers.implementations;


import be.intec.querilesscms.controllers.interfaces.UsersController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersControllerImpl implements UsersController {


    @GetMapping("/profile")
    public String userIndex() {
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
