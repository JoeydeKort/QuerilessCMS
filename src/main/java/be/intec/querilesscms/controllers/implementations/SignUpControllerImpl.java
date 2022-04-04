package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.SignUpController;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignUpControllerImpl implements SignUpController  {

    private final UsersService usersService;

    public SignUpControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/signup")
    public String registration(Model model) {

        model.addAttribute("user", new User());

        return "/signup";
    }

}
