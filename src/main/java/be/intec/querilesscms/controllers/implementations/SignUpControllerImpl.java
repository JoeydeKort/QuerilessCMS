package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.SignUpController;
import be.intec.querilesscms.models.Users;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignUpControllerImpl implements SignUpController  {

    private final UsersService usersService;

    @Autowired
    public SignUpControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/signup")
    public String registration(Model model) {

        model.addAttribute("user", new Users());

        return "/signup";
    }

}
