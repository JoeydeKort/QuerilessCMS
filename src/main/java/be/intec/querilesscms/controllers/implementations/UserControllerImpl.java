package be.intec.querilesscms.controllers.implementations;

import be.intec.querilesscms.controllers.interfaces.UserController;
import be.intec.querilesscms.models.User;
import be.intec.querilesscms.services.interfaces.UsersService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;



@Controller
public class UserControllerImpl implements UserController {

    private final UsersService usersService;


    public UserControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {

        Object principal = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = usersService.findByUserName(username);

        session.setAttribute("user", user);
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
