package be.intec.querilesscms.controllers.implementations;


import be.intec.querilesscms.controllers.interfaces.AdminController;
import be.intec.querilesscms.services.Implementations.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AdminControllerImpl implements AdminController {

    private final UsersServiceImpl usersServiceImpl;

    public AdminControllerImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
